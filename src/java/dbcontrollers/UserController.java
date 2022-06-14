
package dbcontrollers;
import dbmodels.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserController extends Controller {

    
    public int login(String email, String password) {
        PreparedStatement ps; 
        ResultSet r;
        
        String query = "SELECT * FROM usuarios WHERE email = ? AND password = ?";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
        
            r = ps.executeQuery();

            if(r.next()){
                // Se encontro
                return 1;
            }
            
            return 0;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        return 0;
    }
    

    public void register(String email, String password, String name ) {
        PreparedStatement ps; 
        String query = "INSERT INTO usuarios (EMAIL, PASSWORD, NAME) VALUES(?, ?, ?)";
 
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.executeUpdate();
            System.out.println("RegisterController: Registrado");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void getUser(int id) {
        
    }
    
    public User getUser(String email) {
        
        PreparedStatement ps; 
        ResultSet r;
        
        String query = "SELECT * FROM usuarios WHERE email = ? ";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, email);
        
            r = ps.executeQuery();

            if(r.next()){
                int id = r.getInt("id");
                String name = r.getString("name");
                User user = new User(id, email, name);
                return user;
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        return null;
    }
}

