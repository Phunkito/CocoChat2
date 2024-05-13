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
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecuperarContraseñaInterfaz extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoNuevaContraseña;
    private JPasswordField campoConfirmarContraseña;
    private JTextField campoSecurityWord; // Nuevo campo para la palabra de seguridad
    private PasswordRecoveryManager recoveryManager;

    public RecuperarContraseñaInterfaz() {
        recoveryManager = new PasswordRecoveryManager();

        // Configuración de la ventana
        setTitle("Recuperar Contraseña");
        setSize(350, 400); // Aumenta el tamaño para acomodar el nuevo campo
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

        // Nuevo campo para la palabra de seguridad
        JLabel labelSecurityWord = new JLabel("Palabra de seguridad:");
        campoSecurityWord = new JTextField();
        campoSecurityWord.setPreferredSize(new Dimension(200, 30));

        gbc.gridy++;
        panel.add(labelSecurityWord, gbc);
        gbc.gridy++;
        panel.add(campoSecurityWord, gbc);

        JButton botonRecuperarContraseña = new JButton("Recuperar Contraseña");
        botonRecuperarContraseña.setPreferredSize(new Dimension(150, 40));

        botonRecuperarContraseña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String nuevaContraseña = new String(campoNuevaContraseña.getPassword());
                String confirmarContraseña = new String(campoConfirmarContraseña.getPassword());
                String securityWord = campoSecurityWord.getText(); // Obtener la palabra de seguridad
                try {
                    // Llamada al método cambiarContraseña del PasswordRecoveryManager con la palabra de seguridad
                    recoveryManager.cambiarContraseña(usuario, nuevaContraseña, confirmarContraseña, securityWord);
                } catch (InvalidKeySpecException ex) {
                    Logger.getLogger(RecuperarContraseñaInterfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
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
