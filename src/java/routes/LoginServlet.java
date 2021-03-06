/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package routes;

import dbcontrollers.VeterinarioController;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author C
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

    
    public void returnLoginPage(HttpServletRequest request, HttpServletResponse response) {
        
        RequestDispatcher miDispatcher = request.getRequestDispatcher("/login.jsp");
        try {
            miDispatcher.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
       returnLoginPage(request, response); 
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        VeterinarioController rC = new VeterinarioController();
        int result = rC.login(email, password);

        // Inicio de sesion
        
        // Error
        if(result == -1) {
            request.setAttribute("error", "Las credenciales no son correctas");
            returnLoginPage(request, response);
            return;
        }
        
        
            
        // Guardo su id y email en la sesion para corroborar rutas posteriormente
        request.getSession().setAttribute("email-session", email);
        request.getSession().setAttribute("idVeterinario", result);

        request.setAttribute("email", email);
        ServletContext context= getServletContext();
        RequestDispatcher rd= context.getRequestDispatcher("/Home");
        rd.forward(request, response);
        
    }

    
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
