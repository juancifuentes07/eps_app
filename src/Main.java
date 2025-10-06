import models.*;
import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static SistemaGestion sistema;

    public static void main(String[] args) {
        // Intentar cargar los datos desde el archivo
        sistema = SistemaGestion.cargarDatos("sistema.dat");

        // Si no hay datos, inicializarlos
        if (sistema.obtenerMedicos().isEmpty() && sistema.obtenerPacientes().isEmpty()) {
            try {
                java.lang.reflect.Method m = SistemaGestion.class.getDeclaredMethod("inicializarDatos");
                m.setAccessible(true);
                m.invoke(sistema);
                System.out.println("Datos cargados.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Crear e iniciar la ventana principal
        VentanaInicioSesion ventana = new VentanaInicioSesion(sistema);
        ventana.setVisible(true);

        // Guardar los datos automáticamente al cerrar
        ventana.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                sistema.guardarDatos("sistema.dat");
                System.out.println("Datos guardados");
            }
        });
    }
}
