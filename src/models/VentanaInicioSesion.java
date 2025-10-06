package models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaInicioSesion extends JFrame {
    private SistemaGestion sistema;

    public VentanaInicioSesion(SistemaGestion sistema) {
        this.sistema = sistema;
        setTitle("Sistema de Gestión Médica");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel lblCorreo = new JLabel("Correo:");
        JTextField txtCorreo = new JTextField();

        JLabel lblContrasena = new JLabel("Contraseña:");
        JPasswordField txtContrasena = new JPasswordField();

        JButton btnIngresar = new JButton("Iniciar sesión");

        panel.add(lblCorreo);
        panel.add(txtCorreo);
        panel.add(lblContrasena);
        panel.add(txtContrasena);
        panel.add(new JLabel());
        panel.add(btnIngresar);

        add(panel);
        setVisible(true);

        // Acción del botón
        btnIngresar.addActionListener(e -> {
            String correo = txtCorreo.getText();
            String contrasena = new String(txtContrasena.getPassword());
            Object usuario = sistema.iniciarSesion(correo, contrasena);

            if (usuario != null) {
                if (usuario instanceof Medico) {
                    JOptionPane.showMessageDialog(this, "Bienvenido Doctor: " + ((Medico) usuario).obtenerNombre());
                    new VentanaMedico(sistema, (Medico) usuario).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Acceso restringido. Solo médicos pueden iniciar sesión.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

