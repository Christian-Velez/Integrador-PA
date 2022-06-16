/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package routes;

import dbcontrollers.ClienteController;
import dbcontrollers.VeterinarioController;
import dbmodels.Veterinario;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dbmodels.Cliente;
import java.util.ArrayList;


/**
 *
 * @author C
 */
@WebServlet(name = "Clientes", urlPatterns = {"/Clientes"})
public class ClientesServlet extends HttpServlet {
    
    private ClienteController clientesDAO;
    
    public void init() {
        clientesDAO = new ClienteController();
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
                insertCliente(request, response);
                break;
                
            case "EDIT":
                showEditForm(request, response);
                break;
                
            case "UPDATE":
                updateCliente(request, response);
                break;
                
            case "DELETE":
                deleteCliente(request, response);
                break;
            
            default:
                showClientes(request, response);
                break;
        }
    }
    
    private void insertCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idVeterinario = (Integer)request.getSession().getAttribute("idVeterinario");
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        clientesDAO.insertCliente(idVeterinario, nombre, telefono);
        response.sendRedirect("Clientes");
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idCliente = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = clientesDAO.selectCliente(idCliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("clientes-form.jsp");
        request.setAttribute("Cliente", cliente);
        dispatcher.forward(request, response);
    }
    
    private void updateCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        clientesDAO.updateCliente(idCliente, nombre, telefono);
        response.sendRedirect("Clientes");
    }
    
    private void deleteCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idCliente = Integer.parseInt(request.getParameter("id"));
        clientesDAO.deleteCliente(idCliente);
        response.sendRedirect("Clientes");
    }
    
    private void showClientes(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idVeterinario = (Integer)request.getSession().getAttribute("idVeterinario");
        ArrayList<Cliente> clientes = clientesDAO.getAllClientes(idVeterinario);
        request.setAttribute("Clientes", clientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("clientes.jsp");
        dispatcher.forward(request, response);
    }
}
