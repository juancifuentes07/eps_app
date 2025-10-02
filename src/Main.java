import models.*;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Especialidad medicinaGeneral = new Especialidad("Medicina General");
        Especialidad pediatría = new Especialidad("Pediatría");
        Especialidad fisioterapia = new Especialidad("Fisioterapia");
        Especialidad optometria = new Especialidad("Optometria");
        Especialidad cardiología = new Especialidad("Cardiología");

        Medico medico1 = new Medico(
                101, "Carlos", "Pérez",
                "Chapinero", "123456789",
                new Date(90, 5, 10),
                medicinaGeneral, "1234"
        );

        Paciente paciente1 = new Paciente(
                201, "María", "López",
                "Usaquén", "987654321",
                new Date(95, 2, 20), "MaríaLópez@gmail.com"
        );

        // Crear citas
        Date hoy = new Date();
        Cita cita1 = new Cita(1, hoy, paciente1, medico1);
        Cita cita2 = new Cita(2, hoy, paciente1, medico1);

        // Asignar citas al médico
        medico1.asignarCita(cita1);
        medico1.asignarCita(cita2);

        medico1.atenderCita(1, "Descripcion 1", "Acetaminofen");
        System.out.println(cita1.obtenerDescripcion());
        // Mostrar citas de HOY
        System.out.println("Citas del médico " + medico1.obtenerNombre() + " " + medico1.obtenerEspecialidad() + " hoy:");
        List<Cita> citasHoy = medico1.listarCitasPorFecha(hoy);
        for (Cita c : citasHoy) {
            System.out.println(c);
        }
        medico1.verCitasPendientes();
    }
}
