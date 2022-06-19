/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package routes;

import dbcontrollers.EmpleadoController;
import dbcontrollers.VeterinarioController;
import dbmodels.Veterinario;
import dbmodels.Empleado;
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
@WebServlet(name = "EmpleadosServlet", urlPatterns = {"/Empleados"})
public class EmpleadosServlet extends HttpServlet {
    
    EmpleadoController empleadosDAO;
    
    public void init() {
        empleadosDAO = new EmpleadoController();
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
                insertEmpleado(request, response);
                break;
                
            case "EDIT":
                showEditForm(request, response);
                break;
                
            case "UPDATE":
                updateEmpleado(request, response);
                break;
                
            case "DELETE":
                deleteEmpleado(request, response);
                break;
            
            default:
                showEmpleados(request, response);
                break;
        }
    }
    
    private void insertEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idVeterinario = (Integer)request.getSession().getAttribute("idVeterinario");
        String nombre = request.getParameter("nombre");
        int edad = Integer.parseInt(request.getParameter("edad"));
        int pago = Integer.parseInt(request.getParameter("pago"));
        empleadosDAO.insertEmpleado(idVeterinario, nombre, edad, pago);
        response.sendRedirect("Empleados");
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idEmpleado = Integer.parseInt(request.getParameter("id"));
        Empleado empleado = empleadosDAO.selectEmpleado(idEmpleado);
        request.setAttribute("Empleado", empleado);
        RequestDispatcher dispatcher = request.getRequestDispatcher("empleados-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void updateEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        String nombre = request.getParameter("nombre");
        int edad = Integer.parseInt(request.getParameter("edad"));
        int pago = Integer.parseInt(request.getParameter("pago"));
        
        empleadosDAO.updateEmpleado(idEmpleado, nombre, edad, pago);
     
        
        response.sendRedirect("Empleados");
    }
    
    private void deleteEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idEmpleado = Integer.parseInt(request.getParameter("id"));

        empleadosDAO.deleteEmpleado(idEmpleado);


        response.sendRedirect("Empleados");
    }
    
    private void showEmpleados(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idVeterinario = (Integer)request.getSession().getAttribute("idVeterinario");
        
        ArrayList<Empleado> empleados = empleadosDAO.getAllEmpleados(idVeterinario);
        request.setAttribute("Empleados", empleados);
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("empleados.jsp");
        dispatcher.forward(request, response);
    }
    
    
}
