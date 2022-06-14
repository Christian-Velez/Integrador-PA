/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package routes;

import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author C
 */
public class Validator {
    
    
    
    static Boolean isUserAuthenticated(HttpServletRequest request) {
        String email = (String)request.getSession().getAttribute("email-session");
        
        System.out.println("EMAIL: " + email);

        if(email != null) {
            return true;
        }
        return false;
    }

}
