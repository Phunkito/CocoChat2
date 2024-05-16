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

public class InvitacionPanel extends JPanel {
    private JLabel nombreUsuario;
    private JLabel tipoInvitacion;

    public InvitacionPanel(String nombreUsuario, String contenidoMensaje) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Cambiar a BoxLayout
        setBackground(Color.WHITE); // Fondo blanco para el panel de mensaje

        // Configuración del nombre del usuario
        this.nombreUsuario = new JLabel(nombreUsuario);
        this.nombreUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        this.nombreUsuario.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        this.nombreUsuario.setAlignmentX(CENTER_ALIGNMENT); // Alinear al centro
        add(this.nombreUsuario);

        // Configuración del contenido del mensaje
        this.tipoInvitacion = new JLabel(contenidoMensaje);
        this.tipoInvitacion.setFont(new Font("Arial", Font.BOLD, 11));
        this.tipoInvitacion.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        this.tipoInvitacion.setAlignmentX(CENTER_ALIGNMENT); // Alinear al centro
        add(this.tipoInvitacion);

        setPreferredSize(new Dimension(100, 45));
    }
}