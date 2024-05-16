package cocochat;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import javax.swing.*;
import java.awt.event.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Recuperar_contraseña extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoContraseña;
    private int intentosFallidos;

    public Recuperar_contraseña() {
        
        setTitle("Recuperar cuenta");
        setSize(300, 200);
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
        
        JButton botonRecuperarcontraseña = new JButton("Recuperar Contraseña");
        botonRecuperarcontraseña.setPreferredSize(new Dimension(200, 30));

        botonRecuperarcontraseña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                dispose(); // Cierra esta interfaz
                new RecuperarContraseñaInterfaz();
            }
        });

        gbc.gridy++;
        panel.add(botonRecuperarcontraseña, gbc);
        
        JButton botonRecuperarCuenta = new JButton("Crear Cuenta");
        gbc.gridy++;
        panel.add(botonRecuperarCuenta, gbc);

        botonRecuperarCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 dispose();
                new RegistroInterfaz();
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Recuperar_contraseña::new);
    }
}
