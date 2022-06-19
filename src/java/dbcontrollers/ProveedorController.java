/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbcontrollers;
import dbmodels.Proveedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author C
 */
public class ProveedorController extends Controller {
    
    
     public Proveedor selectProveedor(int idProveedor) {
        Proveedor proveedor = new Proveedor();
        PreparedStatement ps; 
        ResultSet r;

        
        String query = "SELECT * FROM proveedores WHERE id = ?";
        
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idProveedor);
            r = ps.executeQuery();
            
            if(r.next()) {
                int id = r.getInt("id");
                int idVeterinario = r.getInt("idVeterinario");
                String nombre = r.getString("nombre");
                String direccion = r.getString("direccion");
                
                return new Proveedor(id, idVeterinario, nombre, direccion);
            }

            return proveedor;
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return proveedor;
    }
    
    
    public ArrayList<Proveedor> getAllProveedores(int idVeterinario) {
        
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        PreparedStatement ps; 
        ResultSet r;

        
        String query = "SELECT * FROM proveedores WHERE idVeterinario = ?";
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idVeterinario);
            r = ps.executeQuery();
            
            while(r.next()) {
                int id = r.getInt("id");
                int idVet = r.getInt("idVeterinario");
                String nombre = r.getString("nombre");
                String direccion = r.getString("direccion");
                
                Proveedor aux = new Proveedor(id, idVeterinario, nombre, direccion);
                proveedores.add(aux);
            }

            return proveedores;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return proveedores;
    }
    
    
    public void insertProveedor(int idVeterinario, String nombre, String direccion) {
        PreparedStatement ps; 
        String query = "INSERT INTO proveedores (idVeterinario, nombre, direccion) VALUES(?, ?, ?)";
 
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idVeterinario);
            ps.setString(2, nombre);
            ps.setString(3, direccion);
            
            ps.executeUpdate();
            System.out.println("ProveedorController: Registrado");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteProveedor(int id) {
        PreparedStatement ps; 
        String query = "DELETE FROM proveedores WHERE id = ?";
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("ProveedorController: Eliminado");
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void updateProveedor(int idProveedor, String nombre, String direccion) {
        PreparedStatement ps; 
        String query = "UPDATE proveedores SET NOMBRE = ?, DIRECCION = ? WHERE id = ?";
        
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, direccion);
            ps.setInt(3, idProveedor);
            
            ps.executeUpdate();
            System.out.println("ProveedorController: Actualizado");
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
