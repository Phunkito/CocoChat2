/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cocochat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author SvrusLink
 */
public class MenuEliminarAmigos extends JPanel {

    private JLabel nameLabel;
    private JButton friendButton;

    public MenuEliminarAmigos(String userName) {
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
                System.out.print("Hola");
            }
        });
        friendButton = new JButton("Eliminar amigo");
        friendButton.setBackground(new Color(128, 0, 0)); // Botón verde
        friendButton.setForeground(Color.WHITE); // Texto del botón en blanco
        friendButton.setFont(new Font("Arial", Font.BOLD, 10)); // Letra más pequeña para el botón
        friendButton.setPreferredSize(new Dimension(120, 20)); // Botón más pequeño
        panelBotones.add(friendButton);

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.add(nameLabel);
        userInfoPanel.setOpaque(false);

        add(userInfoPanel, BorderLayout.WEST);
        add(panelBotones, BorderLayout.EAST);
        add(invisibleButton, BorderLayout.CENTER);
        setPreferredSize(new Dimension(280, 30)); // Tamaño del recuadro ajustado
    }
}
