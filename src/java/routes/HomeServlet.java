/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package routes;

import dbcontrollers.VeterinarioController;
import dbmodels.Veterinario;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author C
 */
@WebServlet(name = "Home", urlPatterns = {"/Home"})
public class HomeServlet extends HttpServlet {
    
    VeterinarioController veterinarioDAO;
    
    public void init() {
        veterinarioDAO = new VeterinarioController();
    }

    @Override
    // VIENE AQUI SI EL USUARIO RECARGA LA PAGINA
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // == VALIDACION ===
        boolean isAuthenticated = Validator.isUserAuthenticated(request);
        if(!isAuthenticated) {
            response.sendRedirect("");
            return;
        }
        
        // === INFORMACION GENERAL DEL VETERINARIO ===
        String email = (String)request.getSession().getAttribute("email-session");
        Veterinario user = veterinarioDAO.getVeterinario(email);
        request.setAttribute("Usuario", user);
        
        
        // == RETORNO LA PAGINA ===
        RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
        rd.forward(request, response);
    }
    
    
    // Register y Login
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = (String)request.getAttribute("email");
        
        VeterinarioController uH = new VeterinarioController();
        Veterinario user = uH.getVeterinario(email);
        request.setAttribute("Usuario", user);
        
        // Guardo su id y email en la sesion para corroborar rutas posteriormente
        request.getSession().setAttribute("email-session", email);
        request.getSession().setAttribute("idVeterinario", user.id);
        
        
        response.sendRedirect("Home");
    }
  
}
