package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Medico extends Persona implements Serializable {
    private Especialidad especialidad;
    private String contrasena;
    private String correo;
    private List<Cita> citasAsignadas;

    // Constructor
    public Medico(int numIdentificacion, String nombre, String apellido,
                  String barrio, String telefono, Date fechaNacimiento,
                  Especialidad especialidad, String correo, String contrasena) {
        super(numIdentificacion, nombre, apellido, barrio, telefono, fechaNacimiento);
        this.especialidad = especialidad;
        this.contrasena = contrasena;
        this.correo = correo;
        this.citasAsignadas = new ArrayList<>();
    }

    // Métodos propios de Medico
    public Especialidad obtenerEspecialidad() { return especialidad; }
    public void modificarEspecialidad(Especialidad nuevaEspecialidad) { this.especialidad = nuevaEspecialidad; }

    public String obtenerContrasena() { return contrasena; }
    public String obtenerCorreo() { return correo; }
    public void modificarCorreo(String nuevoCorreo) { this.correo = nuevoCorreo; }
    public void modificarContrasena(String nuevaContrasena) { this.contrasena = nuevaContrasena; }

    public void asignarCita(Cita cita) {
        citasAsignadas.add(cita);
    }

    public List<Cita> listarCitasPorFecha(Date fechaBuscada) {
        List<Cita> citasDelDia = new ArrayList<>();
        for (Cita cita : citasAsignadas) {
            if (esMismaFecha(cita.obtenerFecha(), fechaBuscada)) {
                citasDelDia.add(cita);
            }
        }
        return citasDelDia;
    }

    private boolean esMismaFecha(Date fecha1, Date fecha2) {
        return fecha1.getYear() == fecha2.getYear() &&
                fecha1.getMonth() == fecha2.getMonth() &&
                fecha1.getDate() == fecha2.getDate();
    }

    public List<Cita> obtenerCitasAsignadas() {
        return citasAsignadas;
    }

    public void atenderCita(int idCita, String descripcion, String formula) {
        for (Cita cita : citasAsignadas) {
            if (cita.obtenerIdCita() == idCita) {
                cita.modificarDescripcion(descripcion);
                cita.modificarFormula(formula);
                cita.modificarEstado("Atendida");
                System.out.println("Cita " + idCita + " atendida!");
            }
        }
    }

    public void verCitasPendientes() {
        System.out.println("Citas Pendientes");
        for (Cita cita : citasAsignadas) {
            if ("Pendiente".equals(cita.obtenerEstado())) {
                System.out.println("ID: " + cita.obtenerIdCita() +
                        " | Paciente: " + cita.obtenerPaciente().obtenerNombre() +
                        " " + cita.obtenerPaciente().obtenerApellido() +
                        " | Fecha: " + cita.obtenerFecha());
            }
        }
    }

    @Override
    public String toString() {
        return "Medico{" +
                "id=" + numIdentificacion +
                ", nombre='" + nombre + " " + apellido + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}
