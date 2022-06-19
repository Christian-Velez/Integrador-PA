/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbmodels;

/**
 *
 * @author C
 */
public class Proveedor {
    public int id;
    public int idVeterinario;
    public String nombre;
    public String direccion;
    
    
    public Proveedor() {}
    
    public Proveedor(int id, int idVeterinario, String nombre, String direccion) {
        this.id = id;
        this.idVeterinario = idVeterinario;
        this.nombre = nombre;
        this.direccion = direccion;
    }
    
}
