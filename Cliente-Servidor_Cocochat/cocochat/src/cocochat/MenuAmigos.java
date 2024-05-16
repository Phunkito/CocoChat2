/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cocochat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author SvrusLink
 */
public class MenuAmigos extends JPanel {

    private JLabel nameLabel;
    private JButton friendButton;

    MenuAmigos(String userName, MenuUsuarioInterfaz ventana) {
        setLayout(new BorderLayout(10, 0)); // Espaciado horizontal entre elementos
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borde para el recuadro

        nameLabel = new JLabel(userName);
        nameLabel.setOpaque(true);
        nameLabel.setForeground(Color.BLACK); // Color del texto
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Letra grande para el nombre del usuario
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT); // Alineación a la izquierda

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panelBotones.setOpaque(false); // Hacer el panel transparente

        // Botón invisible
        JButton invisibleButton = new JButton();
        invisibleButton.setBorderPainted(false); // Sin borde visible
        invisibleButton.setContentAreaFilled(false); // Sin fondo visible
        invisibleButton.setFocusPainted(false); // Sin efecto de enfoque
        invisibleButton.setOpaque(false); // Componente transparente
        invisibleButton.setPreferredSize(new Dimension(60, 20)); // Tamaño cero para no ocupar espacio
        invisibleButton.setHorizontalAlignment(SwingConstants.LEFT); // Alineación a la izquierda
        invisibleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChatInterfaz(nameLabel.getText()).setVisible(true);
                ventana.dispose();
            }
        });

        friendButton = new JButton("Matar Amigo");
        friendButton.setBackground(new Color(128, 0, 0)); // Botón rojo
        friendButton.setForeground(Color.WHITE); // Texto del botón en blanco
        friendButton.setFont(new Font("Arial", Font.BOLD, 10)); // Letra más pequeña para el botón
        friendButton.setPreferredSize(new Dimension(110, 20)); // Botón más pequeño
        panelBotones.add(friendButton);

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.add(nameLabel);
        userInfoPanel.setOpaque(false);

        add(userInfoPanel, BorderLayout.WEST);
        add(panelBotones, BorderLayout.EAST);
        add(invisibleButton, BorderLayout.CENTER);
        setPreferredSize(new Dimension(150, 50)); // Tamaño del recuadro ajustado
    }
}
