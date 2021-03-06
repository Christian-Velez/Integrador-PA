/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package routes;

import dbcontrollers.CitaController;
import dbcontrollers.ClienteController;
import dbcontrollers.VeterinarioController;
import dbmodels.Cita;
import dbmodels.Cliente;
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
@WebServlet(name = "CitasServlet", urlPatterns = {"/Citas"})
public class CitasServlet extends HttpServlet {
    CitaController citasDAO;
    ClienteController clientesDAO;
    
    public void init() {
        citasDAO = new CitaController();
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
                insertCita(request, response);
                break;
                
            case "EDIT":
                showEditForm(request, response);
                break;
                
            case "UPDATE":
                updateCita(request, response);
                break;
                
            case "DELETE":
                deleteCita(request, response);
                break;
            
            default:
                showCitas(request, response);
                break;
        }
    }
    
    private void insertCita(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idVeterinario = (Integer)request.getSession().getAttribute("idVeterinario");
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
        
        citasDAO.insertCita(idVeterinario, idCliente, nombre, fecha);
        response.sendRedirect("Citas");
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idCita = Integer.parseInt(request.getParameter("id"));
        Cita cita = citasDAO.selectCita(idCita);
        
        request.setAttribute("Cita", cita);

        int idVeterinario = (Integer)request.getSession().getAttribute("idVeterinario");
        ArrayList<Cliente> clientes = clientesDAO.getAllClientes(idVeterinario);
        request.setAttribute("Clientes", clientes);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("citas-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void updateCita(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idCita = Integer.parseInt(request.getParameter("idCita"));
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
        citasDAO.updateCita(idCita, idCliente, nombre, fecha);
        response.sendRedirect("Citas");
    }
    
    private void deleteCita(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idCita = Integer.parseInt(request.getParameter("id"));
        citasDAO.deleteCita(idCita);
        response.sendRedirect("Citas");
    }
    
    private void showCitas(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idVeterinario = (Integer)request.getSession().getAttribute("idVeterinario");
        
        ArrayList<Cita> citas = citasDAO.getAllCitas(idVeterinario);
        request.setAttribute("Citas", citas);
        
        ArrayList<Cliente> clientes = clientesDAO.getAllClientes(idVeterinario);
        request.setAttribute("Clientes", clientes);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("citas.jsp");
        dispatcher.forward(request, response);
    }
    
    
    
    
}
