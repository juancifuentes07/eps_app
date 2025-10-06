package models;

import models.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class VentanaMedico extends JFrame {

    private SistemaGestion sistema;
    private Medico medicoActual;
    private JTable tablaCitas;
    private DefaultTableModel modeloTabla;

    public VentanaMedico(SistemaGestion sistema, Medico medicoActual) {
        this.sistema = sistema;
        this.medicoActual = medicoActual;

        setTitle("Panel del Médico - " + medicoActual.obtenerNombre() + " " + medicoActual.obtenerApellido());
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inicializarComponentes();
        cargarCitas();
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Citas asignadas - " + medicoActual.obtenerEspecialidad(), SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(titulo, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Paciente", "Fecha", "Estado"}, 0);
        tablaCitas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaCitas);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();
        JButton btnAtender = new JButton("Atender Cita");
        JButton btnInasistencia = new JButton("Marcar Inasistencia");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnSalir = new JButton("Cerrar Sesión");

        btnAtender.addActionListener(this::accionAtender);
        btnInasistencia.addActionListener(this::accionInasistencia);
        btnActualizar.addActionListener(e -> cargarCitas());
        btnSalir.addActionListener(e -> {
            dispose();
            new VentanaInicioSesion(sistema).setVisible(true);
        });

        panelBotones.add(btnAtender);
        panelBotones.add(btnInasistencia);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnSalir);

        panel.add(panelBotones, BorderLayout.SOUTH);

        add(panel);
    }

    private void cargarCitas() {
        modeloTabla.setRowCount(0);
        List<Cita> citas = medicoActual.obtenerCitasAsignadas();
        for (Cita c : citas) {
            modeloTabla.addRow(new Object[]{
                    c.obtenerIdCita(),
                    c.obtenerPaciente().obtenerNombre() + " " + c.obtenerPaciente().obtenerApellido(),
                    c.obtenerFecha(),
                    c.obtenerEstado()
            });
        }
    }

    private void accionAtender(ActionEvent e) {
        int fila = tablaCitas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una cita primero.");
            return;
        }

        int idCita = (int) modeloTabla.getValueAt(fila, 0);
        String descripcion = JOptionPane.showInputDialog(this, "Descripción de la cita:");
        String formula = JOptionPane.showInputDialog(this, "Fórmula médica o exámenes:");
        if (descripcion != null && formula != null) {
            medicoActual.atenderCita(idCita, descripcion, formula);
            JOptionPane.showMessageDialog(this, "Cita atendida correctamente.");
            cargarCitas();
        }
    }

    private void accionInasistencia(ActionEvent e) {
        int fila = tablaCitas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una cita primero.");
            return;
        }

        int idCita = (int) modeloTabla.getValueAt(fila, 0);
        String obs = JOptionPane.showInputDialog(this, "Motivo u observación de inasistencia:");
        if (obs != null) {
            for (Cita cita : medicoActual.obtenerCitasAsignadas()) {
                if (cita.obtenerIdCita() == idCita) {
                    cita.marcarInasistencia(obs);
                    break;
                }
            }
            JOptionPane.showMessageDialog(this, "Cita marcada como inasistencia.");
            cargarCitas();
        }
    }
}