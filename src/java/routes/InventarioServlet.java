/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package routes;

import dbcontrollers.ProductoController;
import dbcontrollers.TipoProductoController;
import dbcontrollers.VeterinarioController;
import dbmodels.Producto;
import dbmodels.TipoProducto;

import dbmodels.Veterinario;

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
@WebServlet(name = "InventarioServlet", urlPatterns = {"/Inventario"})
public class InventarioServlet extends HttpServlet {

    ProductoController productosDAO;
    TipoProductoController tiposDAO;
    
    public void init() {
        productosDAO = new ProductoController();
        tiposDAO = new TipoProductoController();
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
            case "ADD_PRODUCTO":
                break;
                
            case "EDIT_PRODUCTO":
                break;
                
            case "UPDATE_PRODUCTO":
                break;
                
            case "DELETE_PRODUCTO":
                break;
                
                
            case "ADD_TIPO":
                insertTipo(request, response);
                break;
                
            case "UPDATE_TIPO":
                updateTipo(request, response);
                break;
                
            case "DELETE_TIPO":
                deleteTipo(request, response);
                break;
            
            default:
                showPage(request, response);
                break;
        }
    }
    
    
    private void insertTipo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idVeterinario = (Integer)request.getSession().getAttribute("idVeterinario");
        String nombre = request.getParameter("nombre");
        
        tiposDAO.insertTipo(idVeterinario, nombre);
        response.sendRedirect("Inventario#tipos");
    }

    private void updateTipo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idVeterinario = (Integer)request.getSession().getAttribute("idVeterinario");
        int idTipo = Integer.parseInt(request.getParameter("idTipo"));
        String nombre = request.getParameter("nombre");
        tiposDAO.updateTipo(idTipo, nombre);
        response.sendRedirect("Inventario#tipos");
        
    }
    
    private void deleteTipo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idTipo = Integer.parseInt(request.getParameter("id"));
        tiposDAO.deleteTipo(idTipo);
        response.sendRedirect("Inventario#tipos");
    }
    
    private void showPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idVeterinario = (Integer)request.getSession().getAttribute("idVeterinario");
        
        ArrayList<TipoProducto> tipos = tiposDAO.getAllTipos(idVeterinario);
        request.setAttribute("Tipos", tipos);
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("inventario.jsp");
        dispatcher.forward(request, response);
    }
    
    
    
}
