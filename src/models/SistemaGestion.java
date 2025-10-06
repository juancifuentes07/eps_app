package models;

import java.io.*;
import java.util.*;

public class SistemaGestion implements Serializable {
    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private List<Cita> citas;

    public SistemaGestion() {
        pacientes = new ArrayList<>();
        medicos = new ArrayList<>();
        citas = new ArrayList<>();
        inicializarDatos();
    }

    public void registrarPaciente(Paciente paciente) {
        pacientes.add(paciente);
        System.out.println("Paciente registrado");
    }

    public void registrarMedico(Medico medico) {
        medicos.add(medico);
        System.out.println("Medico registrado");
    }

    public void agendarCita(Cita cita) {
        citas.add(cita);
        cita.obtenerMedico().asignarCita(cita);
        System.out.println("Cita agendada para " + cita.obtenerPaciente().obtenerNombre() +
                " con el Dr. " + cita.obtenerMedico().obtenerNombre());
    }

    public Paciente buscarPacienteId(int id) {
        for (Paciente paciente : pacientes) {
            if (paciente.obtenerNumIdentificacion() == id) {
                return paciente;
            }
        }
        return null;
    }

    public Medico buscarMedicoId(int id) {
        for (Medico medico : medicos) {
            if (medico.obtenerNumIdentificacion() == id) {
                return medico;
            }
        }
        return null;
    }

    public Cita buscarCitaId(int id) {
        for (Cita cita : citas) {
            if (cita.obtenerIdCita() == id) {
                return cita;
            }
        }
        return null;
    }

    //Listas
    public void listarPacientes() {
        System.out.println("Listado de pacientes");
        for (Paciente paciente : pacientes) {
            System.out.println(paciente);
        }
    }

    public void listarMedicos() {
        System.out.println("Listado de medicos");
        for (Medico medico : medicos) {
            System.out.println(medico);
        }
    }

    public void listarCitas() {
        System.out.println("Listado de citas");
        for (Cita cita : citas) {
            System.out.println(cita);
        }
    }

    //Persistencia de datos
    public void guardarDatos(String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(this);
            System.out.println("Datos guardados");
        } catch (IOException error) {
            System.out.println("Error al guardar el archivo" + error.getMessage());
        }
    }

    public static SistemaGestion cargarDatos(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (SistemaGestion) ois.readObject();
        } catch (FileNotFoundException error) {
            System.out.println("No se encontró el archivo.");
            return new SistemaGestion();
        } catch (Exception error) {
            System.out.println("Error al leer el archivo" + error.getMessage());
            return new SistemaGestion();
        }
    }

    public List<Paciente> obtenerPacientes() {
        return pacientes;
    }

    public List<Medico> obtenerMedicos() {
        return medicos;
    }

    public List<Cita> obtenerCitas() {
        return citas;
    }

    public Object iniciarSesion(String correo, String contrasena) {
        for (Paciente paciente : pacientes) {
            if (paciente.obtenerCorreo().equals(correo) && paciente.obtenerContrasena().equals(contrasena)) {
                return paciente;
            }
        }
        for (Medico medico : medicos) {
            if (medico.obtenerCorreo().equals(correo) && medico.obtenerContrasena().equals(contrasena)) {
                return medico;
            }
        }
        return null;
    }

    //DATOS PACIENTES, MEDICOS Y CITAS
    private void inicializarDatos() {
        Especialidad cardiologia = new Especialidad("Cardiologia");
        Especialidad medGeneral = new Especialidad("Medical General");
        Especialidad pediatria = new Especialidad("Pediatria");
        Especialidad fisioterapia = new Especialidad("Fisioterapia");
        Especialidad anestesiologia = new Especialidad("Anestesiologia");


        Medico medico1 = new Medico(1001, "Carlos", "Ramírez", "Chapinero", "3101111111",
                new Date(80, 3, 12), cardiologia, "carlos@hospital.com", "1234");

        Medico medico2 = new Medico(1002, "Laura", "Martínez", "Usaquén", "3202222222",
                new Date(85, 7, 5), medGeneral, "laura@hospital.com", "abcd");

        Medico medico3 = new Medico(1003, "Andrés", "Pérez", "Suba", "3003333333",
                new Date(90, 1, 25), pediatria, "andres@hospital.com", "5678");

        registrarMedico(medico1);
        registrarMedico(medico2);
        registrarMedico(medico3);

        Paciente paciente1 = new Paciente(2001, "María", "Gómez", "Teusaquillo", "3114444444",
                new Date(95, 6, 18), "maria@gmail.com");

        Paciente paciente2 = new Paciente(2002, "Juan", "Torres", "Kennedy", "3005555555",
                new Date(99, 2, 8), "juan@gmail.com");

        registrarPaciente(paciente1);
        registrarPaciente(paciente2);

        Cita cita1 = new Cita(1, new Date(125, 9, 10, 7, 0), paciente1, medico1);
        Cita cita2 = new Cita(1, new Date(125, 9, 10, 7, 40), paciente2, medico2);
        Cita cita3 = new Cita(1, new Date(125, 9, 10, 8, 20), paciente2, medico3);

        agendarCita(cita1);
        agendarCita(cita2);
        agendarCita(cita3);
    }
}