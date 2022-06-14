/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package routes;

import dbcontrollers.UserController;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author C
 */
public class Register extends HttpServlet {
    protected String middleWare() {
        return "";
    }
    
    
    // MANDA LA PAGINA
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher miDispatcher = request.getRequestDispatcher("/register.jsp");
        miDispatcher.forward(request, response);
    }

    
    // INSERTA AL USUARIO EN LA BD
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        
        
        UserController rC = new UserController();
        rC.register(email, password, name);
        
        
        // Me olvido de que pasa despues, solo lo redirijo a ese controlador
        // Esta redireccion se manda como "POST"
        // Le mando el email a Home
        request.setAttribute("email", email);
        ServletContext context= getServletContext();
        RequestDispatcher rd= context.getRequestDispatcher("/Home");
        rd.forward(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
