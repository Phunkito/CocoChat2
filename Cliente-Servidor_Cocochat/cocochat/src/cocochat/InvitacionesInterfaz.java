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
import java.util.ArrayList;

public class InvitacionesInterfaz extends JFrame {

    private JPanel panel = new JPanel();
    private JPanel mainPanel = new JPanel();
    ArrayList<InvitacionPanel> Invitaciones = new ArrayList<InvitacionPanel>();

    public InvitacionesInterfaz() {
        // Panel principal con color de fondo
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        Invitaciones.add(new InvitacionPanel("NombreUsuario", "Solicitud de Amistad"));
        Invitaciones.add(new InvitacionPanel("NombreUsuario", "Invitacion Al Grupo"));
        Invitaciones.add(new InvitacionPanel("NombreUsuario", "Invitacion Al Grupo"));
        Invitaciones.add(new InvitacionPanel("NombreUsuario", "Invitacion Al Grupo"));
        mainPanel.setLayout(new BorderLayout());
        this.add(mainPanel);

        // Crear el modelo de lista con datos de ejemplo
        DefaultListModel<JPanel> listModel = new DefaultListModel<>();

        for (InvitacionPanel solicitud : Invitaciones) {
            solicitud.setPreferredSize(new Dimension(140, 50));
            JPanel grupo = new JPanel();
            grupo.add(solicitud);
            grupo.setBackground(Color.white);

            JPanel buttonPanel = new JPanel(); // Panel para botones de aceptar y eliminar
            JButton aceptarButton = new JButton("Aceptar");
            aceptarButton.setPreferredSize(new Dimension(100, 30));
            aceptarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(mainPanel, "Solicitud aceptada: ");
                }
            });
            buttonPanel.add(aceptarButton);

            JButton eliminarButton = new JButton("Eliminar");
            eliminarButton.setPreferredSize(new Dimension(100, 30));
            eliminarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(mainPanel, "Se rechazo la solicitud de: ");
                }
            });
            buttonPanel.add(eliminarButton);
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            buttonPanel.setBackground(Color.white);

            grupo.add(buttonPanel);

            panel.add(grupo);
        }

        JScrollPane scrollPane = new JScrollPane();

        scrollPane.setViewportView(panel);

        // Agregar la lista al centro del panel principal
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Botón de regreso en la esquina inferior derecha
        JButton botonRegresar = new JButton("Regresar");
        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(InvitacionesInterfaz.this, "Regresando...");
                // Código para regresar
                    dispose();
            }
        });
        mainPanel.add(botonRegresar, BorderLayout.SOUTH);

        // Configuración de la ventana
        this.setSize(400, 300);
        this.setTitle("Solicitudes de Amistad");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Evitar que se cierre la ventana
        this.setUndecorated(true); // Quitar bordes y barra de título
        this.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG); // Restaurar barra de título
        this.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        this.setResizable(false); // Evitar que se pueda redimensionar la ventana
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InvitacionesInterfaz());
    }
}
