/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbcontrollers;

/**
 *
 * @author C
 */

import java.sql.Connection;
import java.sql.DriverManager;


public class Controller {
    protected Connection c; //protegida o privada con metodo gate
    
    public Controller() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //conexión con la bd
            c  = DriverManager.getConnection("jdbc:mysql://localhost/veterinariaabc","root",""); //dominio para bd fuera del equipo, usuario, contraseña
            
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("no existe");
        } 
    }
}
