/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbmodels;

/**
 *
 * @author C
 */
public class Perro {
    public int id;
    public int idVeterinario;
    public int idCliente; // Dueño
    public String nombreCliente;
    public String nombre; 
    public String enfermedad;
    public boolean atendido;
    
    public Perro() {}
    
    
    public Perro(int id, int idVeterinario, int idCliente, String nombre, String enfermedad, boolean atendido) {
        this.id = id;
        this.idVeterinario = idVeterinario;
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.enfermedad = enfermedad;
        this.atendido = atendido;
    }
    
    public Perro(int id, int idVeterinario, int idCliente, String clienteNombre, String nombre, String enfermedad, boolean atendido) {
        this.id = id;
        this.idVeterinario = idVeterinario;
        this.idCliente = idCliente;
        this.nombreCliente = clienteNombre;
        this.nombre = nombre;
        this.enfermedad = enfermedad;
        this.atendido = atendido;
    }
}
