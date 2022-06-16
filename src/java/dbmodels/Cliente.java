/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbmodels;

/**
 *
 * @author C
 */
public class Cliente {
    public int id;
    public int idVeterinario;
    public String nombre;
    public String telefono;
    
    public Cliente() {}
    
    public Cliente(int id, int idVeterinario, String nombre, String telefono) {
       this.id = id;
       this.idVeterinario = idVeterinario;
       this.nombre = nombre;
       this.telefono = telefono;
    }
    
}
