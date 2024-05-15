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

public class ChatMessagePanel extends JPanel {
    private JLabel nombreUsuarioLabel;
    private JTextArea contenidoMensajeArea;

    public ChatMessagePanel(String nombreUsuario, String contenidoMensaje) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Fondo blanco para el panel de mensaje

        // Configuración del nombre del usuario
        nombreUsuarioLabel = new JLabel(nombreUsuario);
        nombreUsuarioLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nombreUsuarioLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(nombreUsuarioLabel, BorderLayout.NORTH);

        // Configuración del contenido del mensaje
        contenidoMensajeArea = new JTextArea(contenidoMensaje);
        contenidoMensajeArea.setLineWrap(true);
        contenidoMensajeArea.setWrapStyleWord(true);
        contenidoMensajeArea.setEditable(false);
        contenidoMensajeArea.setBackground(Color.LIGHT_GRAY); // Fondo gris para el mensaje
        contenidoMensajeArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(new JScrollPane(contenidoMensajeArea), BorderLayout.CENTER);

        setPreferredSize(new Dimension(300, getPreferredHeight(contenidoMensaje)));
    }

    private int getPreferredHeight(String mensaje) {
        int length = mensaje.length();
        if (length <= 110) {
            return 60;
        } else if (length > 110 && length < 220) {
            return 80;
        } else if (length >= 220 && length < 330) {
            return 100;
        } else if (length >= 330 && length < 440) {
            return 120;
        } else {
            return 130;
        }
    }
}