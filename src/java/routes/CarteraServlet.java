/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package routes;

import dbcontrollers.CarteraController;
import dbcontrollers.VeterinarioController;
import dbmodels.Movimiento;
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
@WebServlet(name = "CarteraServlet", urlPatterns = {"/Cartera"})
public class CarteraServlet extends HttpServlet {

    CarteraController carteraDAO;
    
    public void init() {
        carteraDAO = new CarteraController();
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    @Override
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
                insertMovimiento(request, response);
                break;
                
            case "EDIT":
                showEditForm(request, response);
                break;
                
            case "UPDATE":
                updateMovimiento(request, response);
                break;
                
            case "DELETE":
                deleteMovimiento(request, response);
                break;
            
            default:
                showMovimientos(request, response);
                break;
        }
            
        
        
    }

    
    
    private void insertMovimiento(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idVeterinario = (Integer)request.getSession().getAttribute("idVeterinario");
        String concepto = request.getParameter("concepto");
        String tipo = request.getParameter("tipo");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String fecha = request.getParameter("fecha");
        
        if(tipo.equals("GASTO")) {
            cantidad = cantidad*(-1);
        }
        
        carteraDAO.insertMovimiento(idVeterinario, concepto, cantidad, fecha);
        response.sendRedirect("Cartera");
    }
     
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idMovimiento = Integer.parseInt(request.getParameter("id"));
        Movimiento movimiento = carteraDAO.selectMovimiento(idMovimiento);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("cartera-form.jsp");
        request.setAttribute("Movimiento", movimiento);
        dispatcher.forward(request, response);
    }
    
    private void updateMovimiento(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idMovimiento = Integer.parseInt(request.getParameter("idMovimiento"));
        String concepto = request.getParameter("concepto");
        String tipo = request.getParameter("tipo");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String fecha = request.getParameter("fecha");
        
        if(tipo.equals("GASTO")) {
            cantidad = cantidad*(-1);
        }
        
        carteraDAO.updateMovimiento(idMovimiento, concepto, cantidad, fecha);
        response.sendRedirect("Cartera");
    }
    
    private void deleteMovimiento(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idMovimiento = Integer.parseInt(request.getParameter("id"));
        carteraDAO.deleteMovimiento(idMovimiento);
        response.sendRedirect("Cartera");
    }
    
    
    private void showMovimientos(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idVeterinario = (Integer)request.getSession().getAttribute("idVeterinario");

        ArrayList<Movimiento> movimientos = carteraDAO.getMovimientos(idVeterinario);
        request.setAttribute("Movimientos", movimientos);
        
        int balanceGeneral = carteraDAO.getBalanceGeneral(idVeterinario);
        request.setAttribute("BalanceGeneral", balanceGeneral);
        
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("cartera.jsp");
        dispatcher.forward(request, response);
        
        
    }

   
}
