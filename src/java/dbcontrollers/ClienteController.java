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
import dbmodels.Cliente;
import java.util.ArrayList;

/**
 *
 * @author C
 */
public class ClienteController extends Controller {
    
    
    
    public ArrayList<Cliente> getAllClientes(int idVeterinario) {
        
        ArrayList<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps; 
        ResultSet r;

        String query = "SELECT * FROM clientes WHERE idVeterinario = ?";
        
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idVeterinario);
            r = ps.executeQuery();
            
            while(r.next()) {
                
                int id = r.getInt("id");
                int idVet = r.getInt("idVeterinario");
                String nombre = r.getString("nombre");
                String telefono = r.getString("telefono");
                
                
                Cliente aux = new Cliente(id, idVet, nombre, telefono);
                clientes.add(aux);
            }

            return clientes;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return clientes;
    }
    
    
    
    public Cliente selectCliente(int idCliente) {
        Cliente cliente = new Cliente();
        PreparedStatement ps; 
        ResultSet r;

        String query = "SELECT * FROM clientes WHERE id = ?";
        
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idCliente);
            r = ps.executeQuery();
            
            while(r.next()) {
                
                int id = r.getInt("id");
                int idVet = r.getInt("idVeterinario");
                String nombre = r.getString("nombre");
                String telefono = r.getString("telefono");
                return new Cliente(id, idVet, nombre, telefono);
            }

            return cliente;
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return cliente;
    }
    
    
    public void insertCliente(int idVeterinario, String nombre, String telefono) {
        PreparedStatement ps; 
        String query = "INSERT INTO clientes (idVeterinario, nombre, telefono) VALUES(?, ?, ?)";
 
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idVeterinario);
            ps.setString(2, nombre);
            ps.setString(3, telefono);
            
            ps.executeUpdate();
            System.out.println("ClienteController: Registrado");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateCliente(int idCliente, String nombre, String telefono) {
        PreparedStatement ps; 
        String query = "UPDATE clientes SET NOMBRE = ?, TELEFONO = ? WHERE id = ?";
        
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, telefono);
            ps.setInt(3, idCliente);
            ps.executeUpdate();
            
            System.out.println("ClienteController: Actualizado");
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    public void deleteCliente(int id) {
        PreparedStatement ps; 
        String query = "DELETE FROM clientes WHERE id = ?";
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("ClienteController: Eliminado");
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
