/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbcontrollers;
import dbmodels.Movimiento;
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
public class CarteraController extends Controller {
    
    
    
    public CarteraController() {
        
    }
    
    
    
    public void insertMovimiento(int idVeterinario, String concepto, int cantidad, String fecha) {
        PreparedStatement ps; 
        String query = "INSERT INTO movimientos (idVeterinario, CONCEPTO, CANTIDAD, FECHA) VALUES(?, ?, ?, ?)";

        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idVeterinario);
            ps.setString(2, concepto);
            ps.setInt(3, cantidad);
            ps.setString(4, fecha);
            ps.executeUpdate();
            
            System.out.println("CarteraController: Registrado");
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
    public Movimiento selectMovimiento(int idMovimiento) {
        Movimiento movimiento = new Movimiento();
        PreparedStatement ps; 
        ResultSet r;

        String query = "SELECT * FROM movimientos WHERE id = ?";
        
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idMovimiento);
            r = ps.executeQuery();
            
            while(r.next()) {
                
                int id = r.getInt("id");
                int idVet = r.getInt("idVeterinario");
                String concepto = r.getString("concepto");
                int cantidad = r.getInt("cantidad");
                String fecha = r.getString("fecha");
                
                return new Movimiento(id, idVet, concepto, cantidad, fecha);
            }

            return movimiento;
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return movimiento;
    }
    
    public int getBalanceGeneral(int idVeterinario) {
        PreparedStatement ps; 
        ResultSet r;
        String query = "SELECT SUM(CANTIDAD) AS balanceGeneral FROM movimientos WHERE idVeterinario = ?";
        
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idVeterinario);
            r = ps.executeQuery();

            
            if(r.next()) {
                int balanceGeneral = r.getInt("balanceGeneral");
                return balanceGeneral;
            }
            
            return 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return 0;
    }

    public void updateMovimiento(int idMovimiento, String concepto, int cantidad, String fecha) {
        PreparedStatement ps; 
        String query = "UPDATE movimientos SET CONCEPTO = ?, CANTIDAD = ?, FECHA = ? WHERE id = ?";
        
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, concepto);
            ps.setInt(2, cantidad);
            ps.setString(3, fecha);
            ps.setInt(4, idMovimiento);
            
            ps.executeUpdate();
            System.out.println("CarteraController: Actualizado");
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    
    public void deleteMovimiento(int idMovimiento) {
        PreparedStatement ps; 
        String query = "DELETE FROM movimientos WHERE id = ?";
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idMovimiento);
            ps.executeUpdate();
            System.out.println("CarteraController: Eliminado");
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    public ArrayList<Movimiento> getMovimientos(int idVeterinario) {
        
        ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
        PreparedStatement ps; 
        ResultSet r;
        
        
        
        String query = "SELECT * FROM movimientos WHERE idVeterinario = ?  ORDER BY FECHA DESC";
        
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idVeterinario);
            r = ps.executeQuery();
            
            while(r.next()) {
                int id = r.getInt("id");
                int idVet = r.getInt("idVeterinario");
                String concepto = r.getString("concepto");
                int cantidad = r.getInt("cantidad");
                String fecha = r.getString("fecha");
                
                Movimiento aux = new Movimiento(id, idVeterinario, concepto, cantidad, fecha);
                movimientos.add(aux);
            }

            return movimientos;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return movimientos;
    }

}
