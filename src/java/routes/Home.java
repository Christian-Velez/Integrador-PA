/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package routes;

import dbcontrollers.UserController;
import dbmodels.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Home extends HttpServlet {

    @Override
    // VIENE AQUI SI EL USUARIO RECARGA LA PAGINA
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        Boolean isAuthenticated = Validator.isUserAuthenticated(request);
        if(!isAuthenticated) {
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
        
        String email = (String)request.getSession().getAttribute("email-session");
        System.out.println("Email del usuario: " + email);

        UserController uH = new UserController();
        User user = uH.getUser(email);
        request.setAttribute("Usuario", user);

        RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
        rd.forward(request, response);
      
    }
    

    // VIENE AQUI DESPUES DE LA REDIRECCION DEL SERVLET Register y Login
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = (String)request.getAttribute("email");
        
        UserController uH = new UserController();
        User user = uH.getUser(email);
        request.setAttribute("Usuario", user);
        
        RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
        rd.forward(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
