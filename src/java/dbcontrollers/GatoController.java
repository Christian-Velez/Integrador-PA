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
import java.util.ArrayList;
import dbmodels.Gato;

/**
 *
 * @author C
 */
public class GatoController extends Controller {
 
    public GatoController() {}
    
    
    public Gato selectGato(int idGato) {
        Gato perro = new Gato();
        PreparedStatement ps; 
        ResultSet r;

        String query = ("SELECT "
                + "gatos.id, gatos.idVeterinario, gatos.idCliente, gatos.nombre, gatos.enfermedad, gatos.atendido, clientes.nombre AS clienteNombre"
                + " FROM gatos"
                + " INNER JOIN clientes"
                + " ON gatos.idCliente = clientes.id"
                + " WHERE gatos.id = ?"
                        );
        
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idGato);
            r = ps.executeQuery();
            
            while(r.next()) {
                int id = r.getInt("id");
                int idVeterinario = r.getInt("idVeterinario");
                int idCliente = r.getInt("idCliente");
                String nombre = r.getString("nombre");
                String telefono = r.getString("enfermedad");
                boolean atendido = r.getBoolean("atendido");
                String nombreCliente = r.getString("clienteNombre");
                
                return new Gato(id, idVeterinario, idCliente, nombreCliente, nombre, telefono, atendido);
            }

            return perro;
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return perro;
    }
    
    
    public ArrayList<Gato> getAllGatos(int idVeterinario) {
        
        ArrayList<Gato> gatos = new ArrayList<>();
        PreparedStatement ps; 
        ResultSet r;

        String query = ("SELECT "
                + "gatos.id, gatos.idVeterinario, gatos.idCliente, gatos.nombre, gatos.enfermedad, gatos.atendido, clientes.nombre AS clienteNombre"
                + " FROM gatos"
                + " INNER JOIN clientes"
                + " ON gatos.idCliente = clientes.id"
                + " WHERE gatos.idVeterinario = ?"
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
                String enfermedad = r.getString("enfermedad");
                String clienteNombre = r.getString("clienteNombre");
                boolean atendido = r.getBoolean("atendido");
                
                Gato aux = new Gato(id, idVet, idCliente, clienteNombre, nombre, enfermedad, atendido);
                gatos.add(aux);
            }

            return gatos;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return gatos;
    }
    
    
    public void insertGato(int idVeterinario, int idCliente, String nombre, String enfermedad) {
        PreparedStatement ps; 
        String query = "INSERT INTO gatos (idVeterinario, idCliente, nombre, enfermedad) VALUES(?, ?, ?, ?)";
 
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idVeterinario);
            ps.setInt(2, idCliente);
            ps.setString(3, nombre);
            ps.setString(4, enfermedad);
            
            ps.executeUpdate();
            System.out.println("GatoController: Registrado");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteGato(int id) {
        PreparedStatement ps; 
        String query = "DELETE FROM gatos WHERE id = ?";
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("GatoController: Eliminado");
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void updateGato(int idGato, String nombre, String enfermedad, int idCliente, boolean atendido) {
        PreparedStatement ps; 
        String query = "UPDATE gatos SET NOMBRE = ?, ENFERMEDAD = ?, idCliente = ?, ATENDIDO = ? WHERE id = ?";
        
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, enfermedad);
            ps.setInt(3, idCliente);
            ps.setBoolean(4, atendido);
            ps.setInt(5, idGato);
            
            ps.executeUpdate();
            System.out.println("GatoController: Actualizado");
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
}
