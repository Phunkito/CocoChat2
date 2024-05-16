/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package cocochat;

/**
 *
 * @author alan2
 */
import Modelos.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginInterfaz extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoContraseña;
    private LoginManager loginManager;
    private int intentosFallidos;

    public LoginInterfaz() {
        loginManager = new LoginManager();
        intentosFallidos = 0;

        setTitle("Inicio de sesión");
        setSize(350, 300);
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

        JButton botonIniciarSesion = new JButton("Iniciar Sesión");
        botonIniciarSesion.setPreferredSize(new Dimension(150, 40));

        botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String contrasena = new String(campoContraseña.getPassword());

                if (loginManager.iniciarSesion(usuario, contrasena)) {
                    new MenuUsuarioInterfaz();
                    dispose();
                    intentosFallidos = 0; // Resetear contador de intentos fallidos
                    Usuario user = new Usuario(1, usuario, contrasena, true);//Cambiar el id, por medio de la query se va a colocar
                    //Este pedo del id esta pendiente
                    
                } else {
                    intentosFallidos++; // Incrementar contador de intentos fallidos
                    if (intentosFallidos >= 3) {
                        JOptionPane.showMessageDialog(LoginInterfaz.this, "Demasiados intentos fallidos. Por favor, recupere su contraseña.");
                        dispose(); // Cerrar la ventana actual
                        new RegistroInterfaz(); // Abrir la nueva interfaz para recuperar contraseña
                    } else {
                        JOptionPane.showMessageDialog(LoginInterfaz.this, "Credenciales incorrectas");
                    }
                }
            }
        });

        gbc.gridy++;
        panel.add(botonIniciarSesion, gbc);

        JButton botonRecuperarContraseña = new JButton("Recuperar Contraseña");
        gbc.gridy++;
        panel.add(botonRecuperarContraseña, gbc);

        JButton botonCrearCuenta = new JButton("Crear Cuenta");
        gbc.gridy++;
        panel.add(botonCrearCuenta, gbc);

        botonCrearCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                intentosFallidos = 0; // Resetear contador de intentos fallidos
                dispose(); // Cerrar la ventana actual
                new RegistroInterfaz(); // Abrir la nueva interfaz para registro
            }
        });

        botonRecuperarContraseña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                intentosFallidos = 0; // Resetear contador de intentos fallidos
                dispose(); // Cerrar la ventana actual
                new RecuperarContraseñaInterfaz(); // Abrir la nueva interfaz para recuperar contraseña
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginInterfaz::new);
    }
}
