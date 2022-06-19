/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbcontrollers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import dbmodels.Empleado;
import java.util.ArrayList;

/**
 *
 * @author C
 */
public class EmpleadoController extends Controller{
    
    public ArrayList<Empleado> getAllEmpleados(int idVeterinario) {
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();
        PreparedStatement ps; 
        ResultSet r;

        String query = "SELECT * FROM EMPLEADOS WHERE idVeterinario = ?";
        
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idVeterinario);
            r = ps.executeQuery();
            
            while(r.next()) {
                int id = r.getInt("id");
                int idVet = r.getInt("idVeterinario");
                String nombre = r.getString("nombre");
                int edad = r.getInt("edad");
                int pago = r.getInt("pago");
                
                
                Empleado aux = new Empleado(id, idVeterinario, nombre, edad, pago);
                empleados.add(aux);
                
                
            }

            return empleados;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return empleados;
    }
    
    
    public Empleado selectEmpleado(int idEmpleado) {
        Empleado empleado = new Empleado();
        PreparedStatement ps; 
        ResultSet r;

        String query = "SELECT * FROM EMPLEADOS WHERE id = ?";
        
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idEmpleado);
            r = ps.executeQuery();
            
            while(r.next()) {
                int id = r.getInt("id");
                int idVeterinario = r.getInt("idVeterinario");
                String nombre = r.getString("nombre");
                int edad = r.getInt("edad");
                int pago = r.getInt("pago");
                
                return new Empleado(id, idVeterinario, nombre, edad, pago);
            }

            return empleado;
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return empleado;
    }
    
    public void insertEmpleado(int idVeterinario, String nombre, int edad, int pago) {
        PreparedStatement ps; 
        String query = "INSERT INTO empleados (idVeterinario, nombre, edad, pago) VALUES(?, ?, ?, ?)";
 
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idVeterinario);
            ps.setString(2, nombre);
            ps.setInt(3, edad);
            ps.setInt(4, pago);
            
            ps.executeUpdate();
            System.out.println("EmpleadoController: Registrado");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateEmpleado(int id, String nombre, int edad, int pago) {
        PreparedStatement ps; 
        String query = "UPDATE empleados SET NOMBRE = ?, EDAD = ?, PAGO = ? WHERE id = ?";
        
        try {
            ps = c.prepareStatement(query);
            
            ps.setString(1, nombre);
            ps.setInt(2, edad);
            ps.setInt(3, pago);
            ps.setInt(4, id);
            
            ps.executeUpdate();
            System.out.println("EmpleadoController: Actualizado");
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public void deleteEmpleado(int id) {
        PreparedStatement ps; 
        String query = "DELETE FROM empleados WHERE id = ?";
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("EmpleadosController: Eliminado");
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
