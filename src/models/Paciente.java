package models;

import java.util.Date;

public class Paciente extends Persona {
    private String correo;

    // Constructor
    public Paciente(int numIdentificacion, String nombre, String apellido,
                    String barrio, String telefono, Date fechaNacimiento, String correo) {
        super(numIdentificacion, nombre, apellido, barrio, telefono, fechaNacimiento);
        this.correo = correo;
    }

    // Métodos propios de Paciente
    public String obtenerCorreo() {
        return correo;
    }

    public void modificarCorreo(String nuevoCorreo) {
        this.correo = nuevoCorreo;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + numIdentificacion +
                ", nombre='" + nombre + " " + apellido + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}
