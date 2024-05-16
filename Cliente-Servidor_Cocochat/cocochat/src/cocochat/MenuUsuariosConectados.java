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
public class MenuUsuariosConectados extends JPanel {

    JLabel nameLabel;
    private JLabel statusLabel;
    private JButton friendButton;
    private boolean isOnline;

    MenuUsuariosConectados(String userName, boolean isOnline, MenuUsuarioInterfaz ventana) {
        this.isOnline = isOnline;
        setLayout(new BorderLayout(10, 0)); // Espaciado horizontal entre elementos
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borde para el recuadro

        nameLabel = new JLabel(userName);
        nameLabel.setOpaque(true);
        nameLabel.setForeground(Color.BLACK); // Color del texto
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Letra grande para el nombre del usuario
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT); // Alineación a la izquierda

        statusLabel = new JLabel(isOnline ? "ONLINE" : "OFFLINE");
        statusLabel.setOpaque(true);
        statusLabel.setForeground(Color.BLACK); // Color del texto
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12)); // Letra más pequeña para el estado
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT); // Alineación a la izquierda

        updateStatus();

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panelBotones.setOpaque(false); // Hacer el panel transparente

        friendButton = new JButton("Agregar amigo");
        friendButton.setBackground(new Color(0, 128, 0)); // Botón verde
        friendButton.setForeground(Color.WHITE); // Texto del botón en blanco
        friendButton.setFont(new Font("Arial", Font.BOLD, 10)); // Letra más pequeña para el botón
        friendButton.setPreferredSize(new Dimension(180, 20)); // Botón más pequeño
        panelBotones.add(friendButton);

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.add(nameLabel);
        userInfoPanel.add(statusLabel);
        userInfoPanel.setOpaque(false);

        add(userInfoPanel, BorderLayout.WEST);
        add(panelBotones, BorderLayout.EAST);
        setPreferredSize(new Dimension(150, 50)); // Tamaño del recuadro ajustado
    }
    
    MenuUsuariosConectados(String userName, boolean isOnline, CreacionGruposInterfaz ventana) {
        this.isOnline = isOnline;
        setLayout(new BorderLayout(10, 0)); // Espaciado horizontal entre elementos
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borde para el recuadro

        nameLabel = new JLabel(userName);
        nameLabel.setOpaque(true);
        nameLabel.setForeground(Color.BLACK); // Color del texto
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Letra grande para el nombre del usuario
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT); // Alineación a la izquierda

        statusLabel = new JLabel(isOnline ? "ONLINE" : "OFFLINE");
        statusLabel.setOpaque(true);
        statusLabel.setForeground(Color.BLACK); // Color del texto
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12)); // Letra más pequeña para el estado
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT); // Alineación a la izquierda

        updateStatus();

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

        friendButton = new JButton("Agregar al Grupo");
        friendButton.setBackground(new Color(0, 128, 0)); // Botón verde
        friendButton.setForeground(Color.WHITE); // Texto del botón en blanco
        friendButton.setFont(new Font("Arial", Font.BOLD, 10)); // Letra más pequeña para el botón
        friendButton.setPreferredSize(new Dimension(110, 20)); // Botón más pequeño
        panelBotones.add(friendButton);

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.add(nameLabel);
        userInfoPanel.add(statusLabel);
        userInfoPanel.setOpaque(false);

        add(userInfoPanel, BorderLayout.WEST);
        add(panelBotones, BorderLayout.EAST);
        add(invisibleButton, BorderLayout.CENTER);
        setPreferredSize(new Dimension(150, 50)); // Tamaño del recuadro ajustado
    }
    
    MenuUsuariosConectados(String userName, boolean isOnline) {
        this.isOnline = isOnline;
        setLayout(new BorderLayout(10, 0)); // Espaciado horizontal entre elementos
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borde para el recuadro

        nameLabel = new JLabel(userName);
        nameLabel.setOpaque(true);
        nameLabel.setForeground(Color.BLACK); // Color del texto
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Letra grande para el nombre del usuario
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT); // Alineación a la izquierda

        statusLabel = new JLabel(isOnline ? "ONLINE" : "OFFLINE");
        statusLabel.setOpaque(true);
        statusLabel.setForeground(Color.BLACK); // Color del texto
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12)); // Letra más pequeña para el estado
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT); // Alineación a la izquierda

        updateStatus();

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
            }
        });

        friendButton = new JButton("Agregar amigo");
        friendButton.setBackground(new Color(0, 128, 0)); // Botón verde
        friendButton.setForeground(Color.WHITE); // Texto del botón en blanco
        friendButton.setFont(new Font("Arial", Font.BOLD, 10)); // Letra más pequeña para el botón
        friendButton.setPreferredSize(new Dimension(110, 20)); // Botón más pequeño
        panelBotones.add(friendButton);

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.add(nameLabel);
        userInfoPanel.add(statusLabel);
        userInfoPanel.setOpaque(false);

        add(userInfoPanel, BorderLayout.WEST);
        add(panelBotones, BorderLayout.EAST);
        add(invisibleButton, BorderLayout.CENTER);
        setPreferredSize(new Dimension(150, 50)); // Tamaño del recuadro ajustado
    }

    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
        updateStatus();
    }

    private void updateStatus() {
        if (isOnline) {
            statusLabel.setText("Online");
        } else {
            statusLabel.setText("Offline");
        }
    }
}
