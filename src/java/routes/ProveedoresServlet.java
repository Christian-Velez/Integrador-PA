/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package routes;

import dbcontrollers.ProveedorController;
import dbcontrollers.VeterinarioController;
import dbmodels.Veterinario;
import dbmodels.Proveedor;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author C
 */
@WebServlet(name = "ProveedoresServlet", urlPatterns = {"/Proveedores"})
public class ProveedoresServlet extends HttpServlet {
    ProveedorController proveedoresDAO;
    

    public void init() {
        proveedoresDAO = new ProveedorController();
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        
        // === VALIDACION ===
        boolean isAuthenticated = Validator.isUserAuthenticated(request);
        if(!isAuthenticated) {
            response.sendRedirect("");
            return;
        }
        
        
        // === INFORMACION GENERAL DEL VETERINARIO ===
        String email = (String)request.getSession().getAttribute("email-session");
        VeterinarioController uH = new VeterinarioController();
        Veterinario user = uH.getVeterinario(email);
        request.setAttribute("Usuario", user);
        
        
        String action = request.getParameter("action");
        System.out.println("Action: " + action);
        if(action == null) {
            action = "GET";
        }
        
        switch (action) {
            case "ADD":
                insertProveedor(request, response);
                break;
                
            case "EDIT":
                showEditForm(request, response);
                break;
                
            case "UPDATE":
                updateProveedor(request, response);
                break;
                
            case "DELETE":
                deleteProveedor(request, response);
                break;
            
            default:
                showProveedores(request, response);
                break;
        }
    }
    
    private void insertProveedor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idVeterinario = (Integer)request.getSession().getAttribute("idVeterinario");
        
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        proveedoresDAO.insertProveedor(idVeterinario, nombre, direccion);
        response.sendRedirect("Proveedores");
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idProveedor = Integer.parseInt(request.getParameter("id"));
        Proveedor proveedor = proveedoresDAO.selectProveedor(idProveedor);
        request.setAttribute("Proveedor", proveedor);
        RequestDispatcher dispatcher = request.getRequestDispatcher("proveedores-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void updateProveedor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        proveedoresDAO.updateProveedor(idProveedor, nombre, direccion);
        response.sendRedirect("Proveedores");
    }
    
    private void deleteProveedor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idProveedor = Integer.parseInt(request.getParameter("id"));
        proveedoresDAO.deleteProveedor(idProveedor);
        response.sendRedirect("Proveedores");
    }
    
    private void showProveedores(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idVeterinario = (Integer)request.getSession().getAttribute("idVeterinario");
        ArrayList<Proveedor> proveedores = proveedoresDAO.getAllProveedores(idVeterinario);
        request.setAttribute("Proveedores", proveedores);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("proveedores.jsp");
        dispatcher.forward(request, response);
    }

}
