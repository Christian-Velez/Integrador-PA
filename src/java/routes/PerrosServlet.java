/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package routes;

import dbcontrollers.PerroController;
import dbcontrollers.VeterinarioController;
import dbmodels.Cliente;
import dbmodels.Veterinario;
import dbmodels.Perro;
import dbcontrollers.ClienteController;
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
@WebServlet(name = "Perros", urlPatterns = {"/Perros"})
public class PerrosServlet extends HttpServlet {
    
    PerroController perrosDAO;
    ClienteController clientesDAO;
    
    public void init() {
        perrosDAO = new PerroController();
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
                insertPerro(request, response);
                break;
                
            case "EDIT":
                showEditForm(request, response);
                break;
                
            case "UPDATE":
                updatePerro(request, response);
                break;
                
            case "DELETE":
                deletePerro(request, response);
                break;
            
            default:
                showPerros(request, response);
                break;
        }
    }
    
    private void insertPerro(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idVeterinario = (Integer)request.getSession().getAttribute("idVeterinario");
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String enfermedad = request.getParameter("enfermedad");
        
        perrosDAO.insertPerro(idVeterinario, idCliente, nombre, enfermedad);
        response.sendRedirect("Perros");
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
    }
    
    private void updatePerro(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
    }
    
    private void deletePerro(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
    }
    
    private void showPerros(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idVeterinario = (Integer)request.getSession().getAttribute("idVeterinario");
        
        ArrayList<Perro> perros = perrosDAO.getAllPerros(idVeterinario);
        request.setAttribute("Perros", perros);
        
        ArrayList<Cliente> clientes = clientesDAO.getAllClientes(idVeterinario);
        request.setAttribute("Clientes", clientes);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("perros.jsp");
        dispatcher.forward(request, response);
    }
}
