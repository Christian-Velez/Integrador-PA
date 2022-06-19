/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbmodels;

/**
 *
 * @author C
 */
public class Empleado {
    public int id;
    public int idVeterinario;
    public String nombre;
    public int edad;
    public int pago;
    
    
    public Empleado() {}
    
    public Empleado(int id, int idVeterinario, String nombre, int edad, int pago ) {
        this.id = id;
        this.idVeterinario = idVeterinario;
        this.nombre = nombre;
        this.edad = edad;
        this.pago = pago;
    }
    
    
    
    
}
