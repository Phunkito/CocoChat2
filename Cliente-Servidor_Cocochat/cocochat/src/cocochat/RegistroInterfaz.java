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

public class RegistroInterfaz extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoContraseña;
    private JTextField campoSecurityWord; // Nuevo campo para la respuesta de seguridad

    private RegistroManager registroManager;

    public RegistroInterfaz() {
        registroManager = new RegistroManager();

        setTitle("Registro de Usuario");
        setSize(350, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.decode("#7990f8"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 5, 10);

        gbc.gridy++;

        JLabel labelUsuario = new JLabel("Usuario:");
        campoUsuario = new JTextField();
        campoUsuario.setPreferredSize(new Dimension(200, 30));

        panel.add(labelUsuario, gbc);
        gbc.gridy++;
        panel.add(campoUsuario, gbc);

        JLabel labelContraseña = new JLabel("Contraseña:");
        campoContraseña = new JPasswordField();
        campoContraseña.setPreferredSize(new Dimension(200, 30));

        gbc.gridy++;
        panel.add(labelContraseña, gbc);
        gbc.gridy++;
        panel.add(campoContraseña, gbc);

        JLabel labelSecurityWord = new JLabel("Como se llama tu primera mascota?:");
        campoSecurityWord = new JTextField();
        campoSecurityWord.setPreferredSize(new Dimension(200, 30));

        gbc.gridy++;
        panel.add(labelSecurityWord, gbc);
        gbc.gridy++;
        panel.add(campoSecurityWord, gbc);

        JButton botonCrearUsuario = new JButton("Crear Usuario");
        botonCrearUsuario.setPreferredSize(new Dimension(150, 40));
        JButton botonVolver = new JButton("Regresar");
        botonVolver.setPreferredSize(new Dimension(150, 40));

        botonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose(); // Cerrar la ventana actual
                new LoginInterfaz(); // Abrir la nueva interfaz para registro
            }

        });

        botonCrearUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String contraseña = new String(campoContraseña.getPassword());
                String securityWord = campoSecurityWord.getText(); // Obtener la respuesta de seguridad

                if (registroManager.crearUsuario(usuario, contraseña, securityWord)) {
                    JOptionPane.showMessageDialog(RegistroInterfaz.this, "Usuario creado exitosamente");
                    dispose(); // Cerrar la ventana actual después de crear el usuario
                    new LoginInterfaz(); // Abrir la interfaz de inicio de sesión
                } else {
                    JOptionPane.showMessageDialog(RegistroInterfaz.this, "Error al crear el usuario");
                }
            }
        });

        gbc.gridy++;
        panel.add(botonVolver, gbc);
        gbc.gridy++;
        panel.add(botonCrearUsuario, gbc);

        add(panel);
        setVisible(true);
    }
}
