/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cocochat;

/**
 *
 * @author alan2
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecuperarContraseñaInterfaz extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoNuevaContraseña;
    private JPasswordField campoConfirmarContraseña;

    public RecuperarContraseñaInterfaz() {
        // Configuración de la ventana
        setTitle("Recuperar Contraseña");
        setSize(350, 320);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.decode("#7990f8"));

        // Constraints para controlar la ubicación de los componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 5, 10);

        // Componentes
        JLabel labelUsuario = new JLabel("Usuario a recuperar:");
        campoUsuario = new JTextField();
        campoUsuario.setPreferredSize(new Dimension(200, 30));

        panel.add(labelUsuario, gbc);
        gbc.gridy++;
        panel.add(campoUsuario, gbc);

        JLabel labelNuevaContraseña = new JLabel("Nueva Contraseña:");
        campoNuevaContraseña = new JPasswordField();
        campoNuevaContraseña.setPreferredSize(new Dimension(200, 30));

        gbc.gridy++;
        panel.add(labelNuevaContraseña, gbc);
        gbc.gridy++;
        panel.add(campoNuevaContraseña, gbc);

        JLabel labelConfirmarContraseña = new JLabel("Confirmar Contraseña:");
        campoConfirmarContraseña = new JPasswordField();
        campoConfirmarContraseña.setPreferredSize(new Dimension(200, 30));

        gbc.gridy++;
        panel.add(labelConfirmarContraseña, gbc);
        gbc.gridy++;
        panel.add(campoConfirmarContraseña, gbc);

        JButton botonRecuperarContraseña = new JButton("Recuperar Contraseña");
        botonRecuperarContraseña.setPreferredSize(new Dimension(150, 40));

        botonRecuperarContraseña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para recuperar la contraseña
                // Implementa la lógica de recuperación de contraseña aquí
            }
        });

        gbc.gridy++;
        panel.add(botonRecuperarContraseña, gbc);

        JButton botonInicioSesion = new JButton("Inicio de Sesión");
        botonInicioSesion.setPreferredSize(new Dimension(150, 40));

        botonInicioSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la interfaz de inicio de sesión
                dispose(); // Cierra esta interfaz
                new LoginInterfaz(); // Abre la interfaz de inicio de sesión
            }
        });

        gbc.gridy++;
        panel.add(botonInicioSesion, gbc);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RecuperarContraseñaInterfaz::new);
    }
}
