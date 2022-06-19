/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbmodels;

/**
 *
 * @author C
 */
public class Producto {
    public int id;
    public int idVeterinario;
    
    public int idTipo;
    public String tipoNombre;
    
    public int idProveedor;
    public String proveedorNombre;
    
    public String nombre;
    public int cantidad;
    
    public Producto() {}
    
    
    public Producto(int id, int idVeterinario, int idTipo, String tipoNombre, int idProveedor, String proveedorNombre, String nombre, int cantidad) {
        this.id = id;
        this.idVeterinario = idVeterinario;
        this.idTipo = idTipo;
        this.tipoNombre = tipoNombre;
        this.idProveedor = idProveedor;
        this.proveedorNombre = proveedorNombre;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
}
