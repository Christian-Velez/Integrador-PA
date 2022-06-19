/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbmodels;

/**
 *
 * @author C
 */
public class TipoProducto {
    public int id;
    public int idVeterinario;
    public String nombre;
    
    
    public TipoProducto() { } 
    
    public TipoProducto(int id, int idVeterinario, String nombre) {
        this.id = id;
        this.idVeterinario = idVeterinario;
        this.nombre = nombre;
    }
}
