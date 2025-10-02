package models;

import java.io.Serializable;
import java.util.Date;

public class Cita implements Serializable {
    private int idCita;
    private Date fechaHora;           // Fecha y hora de la cita
    private String estado;            // "Pendiente", "Atendida", "Inasistencia"
    private String descripcion;       // Descripción de la cita
    private String formula;           // Fórmula médica o exámenes
    private String observacion;       // Nota adicional (ej: paciente no asistió)
    private Paciente paciente;        // Relación con paciente
    private Medico medico;            // Relación con médico

    // Constructor
    public Cita(int idCita, Date fechaHora, Paciente paciente, Medico medico) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.paciente = paciente;
        this.medico = medico;
        this.estado = "Pendiente";
        this.descripcion = "";
        this.formula = "";
        this.observacion = "";
    }

    // Métodos
    public void atenderCita(String descripcion, String formula) {
        this.descripcion = descripcion;
        this.formula = formula;
        this.estado = "Atendida";
    }

    public void marcarInasistencia(String observacion) {
        this.estado = "Inasistencia";
        this.observacion = observacion;
    }

    // Getters y modificadores
    public int obtenerIdCita() { return idCita; }
    public Date obtenerFecha() { return fechaHora; }

    public String obtenerEstado() { return estado; }
    public void modificarEstado(String estado) { this.estado = estado; }

    public String obtenerDescripcion() { return descripcion; }
    public void modificarDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String obtenerFormula() { return formula; }
    public void modificarFormula(String formula) { this.formula = formula; }

    public String obtenerObservacion() { return observacion; }
    public void modificarObservacion(String observacion) { this.observacion = observacion; }

    public Paciente obtenerPaciente() { return paciente; }
    public Medico obtenerMedico() { return medico; }

    @Override
    public String toString() {
        return "Cita{" +
                "id=" + idCita +
                ", fecha=" + fechaHora +
                ", paciente=" + paciente.obtenerNombre() + " " + paciente.obtenerApellido() +
                ", medico=" + medico.obtenerNombre() + " " + medico.obtenerApellido() +
                ", estado=" + estado +
                (observacion.isEmpty() ? "" : ", observacion=" + observacion) +
                '}';
    }
}