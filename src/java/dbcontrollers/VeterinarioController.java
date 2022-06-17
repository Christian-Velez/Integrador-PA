
package dbcontrollers;
import dbmodels.Veterinario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VeterinarioController extends Controller {

    
    public int login(String email, String password) {
        PreparedStatement ps; 
        ResultSet r;
        
        String query = "SELECT * FROM veterinarios WHERE email = ? AND password = ?";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
        
            r = ps.executeQuery();

            if(r.next()){
                // Se encontro
                int veterinarioId = r.getInt("id");
                return veterinarioId;
            }
            
            return -1;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        return -1;
    }
    

    public void register(String email, String password, String nombre ) {
        PreparedStatement ps; 
        String query = "INSERT INTO veterinarios (EMAIL, PASSWORD, NOMBRE) VALUES(?, ?, ?)";
 
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, nombre);
            ps.executeUpdate();
            System.out.println("RegisterController: Registrado");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void getVeterinarioPassword(int id) {
        
    }
    
    public Veterinario getVeterinario(String email) {
        
        PreparedStatement ps; 
        ResultSet r;
        
        String query = "SELECT * FROM veterinarios WHERE email = ? ";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, email);
        
            r = ps.executeQuery();

            if(r.next()){
                int id = r.getInt("id");
                String name = r.getString("nombre");
                Veterinario veterinario = new Veterinario(id, email, name);
                veterinario.password = r.getString("password");
                
                return veterinario;
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        return null;
    }
    
    
    
    public void updateVeterinario(int idVeterinario, String nombre, String password) {
        
        PreparedStatement ps; 
        String query = "UPDATE veterinarios SET NOMBRE = ?, PASSWORD = ? WHERE id = ?";
        
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, password);
            ps.setInt(3, idVeterinario);
            
            ps.executeUpdate();
            System.out.println("VeterinarioController: Actualizado");
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

