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
import dbmodels.Perro;
import java.util.ArrayList;

/**
 *
 * @author C
 */
public class PerroController extends Controller {
    
    public PerroController() {}
    
    
    public ArrayList<Perro> getAllPerros(int idVeterinario) {
        
        ArrayList<Perro> perros = new ArrayList<>();
        PreparedStatement ps; 
        ResultSet r;

        String query = ("SELECT "
                + "perros.id, perros.idVeterinario, perros.idCliente, perros.nombre, perros.enfermedad, perros.atendido, clientes.nombre AS clienteNombre"
                + " FROM perros"
                + " INNER JOIN clientes"
                + " ON perros.idCliente = clientes.id"
                + " WHERE perros.idVeterinario = ?"
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
                
                Perro aux = new Perro(id, idVet, idCliente, clienteNombre, nombre, enfermedad, atendido);
                perros.add(aux);
            }

            return perros;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return perros;
    }
    
    
    public void insertPerro(int idVeterinario, int idCliente, String nombre, String enfermedad) {
        PreparedStatement ps; 
        String query = "INSERT INTO perros (idVeterinario, idCliente, nombre, enfermedad) VALUES(?, ?, ?, ?)";
 
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idVeterinario);
            ps.setInt(2, idCliente);
            ps.setString(3, nombre);
            ps.setString(4, enfermedad);
            
            ps.executeUpdate();
            System.out.println("PerroController: Registrado");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
