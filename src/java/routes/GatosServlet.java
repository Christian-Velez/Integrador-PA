/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package routes;

import dbcontrollers.ClienteController;
import dbcontrollers.GatoController;
import dbcontrollers.PerroController;
import dbcontrollers.VeterinarioController;
import dbmodels.Cliente;
import dbmodels.Gato;
import dbmodels.Perro;
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
@WebServlet(name = "GatosServlet", urlPatterns = {"/Gatos"})
public class GatosServlet extends HttpServlet {

    GatoController gatosDAO;
    ClienteController clientesDAO;
    
    public void init() {
        gatosDAO = new GatoController();
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
                insertGato(request, response);
                break;
                
            case "EDIT":
                showEditForm(request, response);
                break;
                
            case "UPDATE":
                updateGato(request, response);
                break;
                
            case "DELETE":
                deleteGato(request, response);
                break;
            
            default:
                showGatos(request, response);
                break;
        }
    }
    
    private void insertGato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idVeterinario = (Integer)request.getSession().getAttribute("idVeterinario");
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String enfermedad = request.getParameter("enfermedad");
        
        gatosDAO.insertGato(idVeterinario, idCliente, nombre, enfermedad);
        response.sendRedirect("Gatos");
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idGato = Integer.parseInt(request.getParameter("id"));
        Gato gato = gatosDAO.selectGato(idGato);
        request.setAttribute("Gato", gato);

        int idVeterinario = (Integer)request.getSession().getAttribute("idVeterinario");
        ArrayList<Cliente> clientes = clientesDAO.getAllClientes(idVeterinario);
        request.setAttribute("Clientes", clientes);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("gatos-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void updateGato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idGato = Integer.parseInt(request.getParameter("idGato"));
        String nombre = request.getParameter("nombre");
        String enfermedad = request.getParameter("enfermedad");
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String atendidoString = request.getParameter("atendido");
        
        boolean atendido = (atendidoString.equals("Si"));
        
        gatosDAO.updateGato(idGato, nombre, enfermedad, idCliente, atendido);
        response.sendRedirect("Gatos");
    }
    
    private void deleteGato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idGato = Integer.parseInt(request.getParameter("id"));
        gatosDAO.deleteGato(idGato);
        response.sendRedirect("Gatos");
    }
    
    private void showGatos(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idVeterinario = (Integer)request.getSession().getAttribute("idVeterinario");
        
        ArrayList<Gato> gatos = gatosDAO.getAllGatos(idVeterinario);
        request.setAttribute("Gatos", gatos);
        
        ArrayList<Cliente> clientes = clientesDAO.getAllClientes(idVeterinario);
        request.setAttribute("Clientes", clientes);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("gatos.jsp");
        dispatcher.forward(request, response);
    }
}
