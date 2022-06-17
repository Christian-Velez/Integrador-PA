/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbmodels;

/**
 *
 * @author C
 */
public class Movimiento {
    
    public int id;
    public int idVeterinario;
    public String concepto;
    public int cantidad;
    public String fecha;
    
    public Movimiento() { }
    
    public Movimiento(int id, int idVeterinario, String concepto, int cantidad, String fecha) {
        this.id = id;
        this.idVeterinario = idVeterinario;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }
    
    
    
}
