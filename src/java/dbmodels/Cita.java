/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbmodels;

/**
 *
 * @author C
 */
public class Cita {
    public int id;
    public int idVeterinario;
    
    public int idCliente;
    public String nombreCliente;
    
    public String nombre;
    public String fecha;


    public Cita() {}
    
    public Cita(int id, int idVeterinario, int idCliente, String nombreCliente, String nombre, String fecha) {
        this.id = id;
        this.idVeterinario = idVeterinario;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.nombre = nombre;
        this.fecha = fecha;
    }
    
}

