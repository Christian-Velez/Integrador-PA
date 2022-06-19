/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbcontrollers;

import dbmodels.Producto;
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
public class ProductoController extends Controller {
    
    
    public ArrayList<Producto> getAllProductos(int idVeterinario) {
        ArrayList<Producto> productos = new ArrayList<Producto>();
        PreparedStatement ps; 
        ResultSet r;
        String query = ("SELECT "
                + "productos.id, productos.idTipo, productos.idVeterinario, productos.idProveedor, productos.nombre, productos.cantidad, "
                + " proveedores.nombre AS proveedorNombre, tiposdeproducto.nombre AS tipoNombre"
                + " FROM productos"
                + " INNER JOIN proveedores"
                + " ON productos.idProveedor = proveedores.id"
                + " INNER JOIN tiposdeproducto"
                + " ON productos.idTipo = tiposdeproducto.id"
                + " WHERE productos.idVeterinario = ?"
                        );
        
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idVeterinario);
            r = ps.executeQuery();
            
            while(r.next()) {
                int id = r.getInt("id");
                int idVet = r.getInt("idVeterinario");
                int idTipo = r.getInt("idTipo");
                String tipoNombre = r.getString("tipoNombre");
                int idProveedor = r.getInt("idProveedor");
                String proveedorNombre = r.getString("proveedorNombre");
                String nombre = r.getString("nombre");
                int cantidad = r.getInt("cantidad");
                
                Producto aux = new Producto(id, idVet, idTipo, tipoNombre, idProveedor, proveedorNombre, nombre, cantidad);
                productos.add(aux);
            }

            return productos;
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return productos;
    }
    
    public Producto selectProducto(int idProducto) {
        Producto producto = new Producto();
        PreparedStatement ps; 
        ResultSet r;

        String query = ("SELECT "
                + "productos.id, productos.idTipo, productos.idVeterinario, productos.idProveedor, productos.nombre, productos.cantidad, "
                + " proveedores.nombre AS proveedorNombre, tiposdeproducto.nombre AS tipoNombre"
                + " FROM productos"
                + " INNER JOIN proveedores"
                + " ON productos.idProveedor = proveedores.id"
                + " INNER JOIN tiposdeproducto"
                + " ON productos.idTipo = tiposdeproducto.id"
                + " WHERE productos.id = ?"
                        );
        
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idProducto);
            r = ps.executeQuery();
            
            if(r.next()) {
                int id = r.getInt("id");
                int idVet = r.getInt("idVeterinario");
                
                int idTipo = r.getInt("idTipo");
                String tipoNombre = r.getString("tipoNombre");
                
                int idProveedor = r.getInt("idProveedor");
                String proveedorNombre = r.getString("proveedorNombre");
                
                String nombre = r.getString("nombre");
                int cantidad = r.getInt("cantidad");
                
                
                return new Producto(id, idVet, idTipo, tipoNombre, idProveedor, proveedorNombre, nombre, cantidad);
                
            }

            return producto;
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return producto;
    }
    
    
    public void insertProducto(int idTipo, int idVeterinario, int idProveedor, String nombre, int cantidad) {
        PreparedStatement ps; 
        String query = "INSERT INTO productos (idTipo, idVeterinario, idProveedor, nombre, cantidad) VALUES(?, ?, ?, ?, ?)";
 
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idTipo);
            ps.setInt(2, idVeterinario);
            ps.setInt(3, idProveedor);
            ps.setString(4, nombre);
            ps.setInt(5, cantidad);

            
            ps.executeUpdate();
            System.out.println("ProductoController: Registrado");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteProducto(int id) {
        PreparedStatement ps; 
        String query = "DELETE FROM productos WHERE id = ?";
        
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("ProductoController: Eliminado");
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
    public void updateProducto(int idTipo, int idProveedor, String nombre, int cantidad, int idProducto) {
        PreparedStatement ps; 
        String query = "UPDATE productos SET idTipo = ?, idProveedor = ?, nombre = ?, cantidad = ? WHERE id = ?";
        
        try {
            ps = c.prepareStatement(query);
            
            ps.setInt(1, idTipo);
            ps.setInt(2, idProveedor);
            ps.setString(3, nombre);
            ps.setInt(4, cantidad);
            ps.setInt(5, idProducto);

            
            ps.executeUpdate();
            System.out.println("ProductoController: Actualizado");
            
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    
    
    
}
