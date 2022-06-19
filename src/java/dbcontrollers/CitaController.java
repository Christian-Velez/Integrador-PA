/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbcontrollers;
import dbmodels.Cita;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author C
 */
public class CitaController extends Controller{
    
    
    public ArrayList<Cita> getAllCitas(int idVeterinario) {
        ArrayList<Cita> citas = new ArrayList<Cita>();
        PreparedStatement ps; 
        ResultSet r;

        String query = ("SELECT "
                + "citas.id, citas.idVeterinario, citas.idCliente, citas.nombre, citas.fecha, clientes.nombre AS nombreCliente"
                + " FROM citas"
                + " INNER JOIN clientes"
                + " ON citas.idCliente = clientes.id"
                + " WHERE citas.idVeterinario = ?"
                        );
        
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idVeterinario);
            r = ps.executeQuery();
            
            while(r.next()) {
                int id = r.getInt("id");
                int idVet = r.getInt("idVeterinario");
                int idCliente = r.getInt("idCliente");
                String nombre = r.getString("nombre");
                String fecha = r.getString("fecha");
                String nombreCliente = r.getString("nombreCliente");
                
                
                Cita aux = new Cita(id, idVeterinario, idCliente, nombreCliente, nombre, fecha);
                
                citas.add(aux);
            }

            return citas;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return citas;
    }
    
    public void insertCita(int idVeterinario, int idCliente, String nombre, String fecha) {
        PreparedStatement ps; 
        String query = "INSERT INTO citas (idVeterinario, idCliente, nombre, fecha) VALUES(?, ?, ?, ?)";
 
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idVeterinario);
            ps.setInt(2, idCliente);
            ps.setString(3, nombre);
            ps.setString(4, fecha);
            
            ps.executeUpdate();
            System.out.println("CitaController: Registrado");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Cita selectCita(int idCita) {
        Cita cita = new Cita();
        PreparedStatement ps; 
        ResultSet r;

        String query = ("SELECT "
                + "citas.id, citas.idVeterinario, citas.idCliente, citas.nombre, citas.fecha, clientes.nombre AS nombreCliente"
                + " FROM citas"
                + " INNER JOIN clientes"
                + " ON citas.idCliente = clientes.id"
                + " WHERE citas.id = ?"
                        );
        
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idCita);
            r = ps.executeQuery();
            
            if(r.next()) {
                int id = r.getInt("id");
                int idVet = r.getInt("idVeterinario");
                int idCliente = r.getInt("idCliente");
                String nombre = r.getString("nombre");
                String fecha = r.getString("fecha");
                String nombreCliente = r.getString("nombreCliente");
                
                return new Cita(id, idVet, idCliente, nombreCliente, nombre, fecha);
            }

            return cita;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return cita;
    }
    
    public void deleteCita(int idCita) {
        PreparedStatement ps; 
        String query = "DELETE FROM citas WHERE id = ?";
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idCita);
            ps.executeUpdate();
            System.out.println("CitaController: Eliminado");
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void updateCita(int idCita, int idCliente, String nombre, String fecha) {
        PreparedStatement ps; 
        String query = "UPDATE citas SET NOMBRE = ?, FECHA = ?, idCliente = ? WHERE id = ?";
        
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, fecha);
            
            ps.setInt(3, idCliente);
            ps.setInt(4, idCita);
            
            ps.executeUpdate();
            System.out.println("CitaController: Actualizado");
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
