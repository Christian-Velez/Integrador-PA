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
    int id;
    int idVeterinario;
    int idCliente; // Dueño
    String nombre; 
    String enfermedad;
    boolean atendido;
    
    
    public Perro(int id, int idVeterinario, int idCliente, String nombre, String enfermedad, boolean atendido) {
        this.id = id;
        this.idVeterinario = idVeterinario;
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.enfermedad = enfermedad;
        this.atendido = atendido;
    }
}
