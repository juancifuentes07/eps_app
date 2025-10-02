package models;

import java.io.Serializable;
import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public abstract class Persona implements Serializable {
    protected int numIdentificacion;
    protected String nombre;
    protected String apellido;
    protected String barrio;
    protected String telefono;
    protected Date fechaNacimiento;

    // Constructor
    public Persona(int numIdentificacion, String nombre, String apellido,
                   String barrio, String telefono, Date fechaNacimiento) {
        this.numIdentificacion = numIdentificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.barrio = barrio;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int obtenerNumIdentificacion() { return numIdentificacion; }

    public String obtenerNombre() { return nombre; }
    public void modificarNombre(String nuevoNombre) { this.nombre = nuevoNombre; }

    public String obtenerApellido() { return apellido; }
    public void modificarApellido(String nuevoApellido) { this.apellido = nuevoApellido; }

    public String obtenerBarrio() { return barrio; }
    public void modificarBarrio(String nuevoBarrio) { this.barrio = nuevoBarrio; }

    public String obtenerTelefono() { return telefono; }
    public void modificarTelefono(String nuevoTelefono) { this.telefono = nuevoTelefono; }

    public Date obtenerFechaNacimiento() { return fechaNacimiento; }
    public void modificarFechaNacimiento(Date nuevaFecha) { this.fechaNacimiento = nuevaFecha; }

    public int calcularEdad() {
        LocalDate nacimiento = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate hoy = LocalDate.now();
        return Period.between(nacimiento, hoy).getYears();
    }
}
