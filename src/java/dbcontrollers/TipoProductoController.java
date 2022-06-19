/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbcontrollers;
import java.sql.ResultSet;

import dbmodels.TipoProducto;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author C
 */
public class TipoProductoController extends Controller {
    
    
    public void insertTipo(int idVeterinario, String nombre) {
        PreparedStatement ps; 
        String query = "INSERT INTO tiposdeproducto (idVeterinario, nombre) VALUES(?, ?)";
 
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idVeterinario);
            ps.setString(2, nombre);
            
            ps.executeUpdate();
            System.out.println("TipoController: Registrado");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateTipo(int idTipo, String nombre) {
        PreparedStatement ps; 
        String query = "UPDATE tiposdeproducto SET NOMBRE = ? WHERE id = ?";
        
        try {
            ps = c.prepareStatement(query);
            
            ps.setString(1, nombre);
            ps.setInt(2, idTipo);
            
            ps.executeUpdate();
            System.out.println("TiposController: Actualizado");
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void deleteTipo(int idTipo) {
        PreparedStatement ps; 
        String query = "DELETE FROM tiposdeproducto WHERE id = ?";
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idTipo);
            ps.executeUpdate();
            System.out.println("TiposController: Eliminado");
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
    
    
    public ArrayList<TipoProducto> getAllTipos(int idVeterinario) {
        ArrayList<TipoProducto> tipos = new ArrayList<TipoProducto>();
        PreparedStatement ps; 
        ResultSet r;

        String query = "SELECT * FROM tiposdeproducto WHERE idVeterinario = ?";
        
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idVeterinario);
            r = ps.executeQuery();
            
            while(r.next()) {
                int id = r.getInt("id");
                int idVet = r.getInt("idVeterinario");
                String nombre = r.getString("nombre");
                
                TipoProducto aux = new TipoProducto(id, idVet, nombre);
                tipos.add(aux);
            }

            return tipos;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return tipos;
    }
}
