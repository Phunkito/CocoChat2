/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cocochat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *
 * @author SvrusLink
 */
public class MenuGrupos extends JPanel {

    private JLabel nameLabel;
    private JButton friendButton;

    public MenuGrupos(String GroupName, MenuUsuarioInterfaz ventana) {
        setLayout(new BorderLayout(10, 0)); // Espaciado horizontal entre elementos
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borde para el recuadro

        nameLabel = new JLabel(GroupName);
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

        friendButton = new JButton("Ver detalles");
        friendButton.setBackground(new Color(0, 0, 128)); // Botón azul
        friendButton.setForeground(Color.WHITE); // Texto del botón en blanco
        friendButton.setFont(new Font("Arial", Font.BOLD, 10)); // Letra más pequeña para el botón
        friendButton.setPreferredSize(new Dimension(120, 20)); // Botón más pequeño
        friendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<MenuUsuariosConectados> allUsers = new ArrayList<MenuUsuariosConectados>();
                allUsers.add(new MenuUsuariosConectados("Homelo Chino", true));
                allUsers.add(new MenuUsuariosConectados("Baymax", true));
                allUsers.add(new MenuUsuariosConectados("Homelo Chino", true));
                allUsers.add(new MenuUsuariosConectados("Baymax", true));
                allUsers.add(new MenuUsuariosConectados("Homelo Chino", true));
                allUsers.add(new MenuUsuariosConectados("Baymax", true));
                allUsers.add(new MenuUsuariosConectados("Homelo Chino", true));
                allUsers.add(new MenuUsuariosConectados("Baymax", true));
                allUsers.add(new MenuUsuariosConectados("Homelo Chino", true));
                allUsers.add(new MenuUsuariosConectados("Baymax", true));
                allUsers.add(new MenuUsuariosConectados("Homelo Chino", true));
                
                ArrayList<String> usersotes = new ArrayList<String>();
                for (MenuUsuariosConectados user : allUsers) {
                    usersotes.add(user.nameLabel.getText());
                }
                new MenuDetallesGrupo(nameLabel.getText(), usersotes);
            }
        });
        panelBotones.add(friendButton);

        JPanel GroupInfoPanel = new JPanel();
        GroupInfoPanel.setLayout(new BoxLayout(GroupInfoPanel, BoxLayout.Y_AXIS));
        GroupInfoPanel.add(nameLabel);
        GroupInfoPanel.setOpaque(false);

        add(GroupInfoPanel, BorderLayout.WEST);
        add(panelBotones, BorderLayout.EAST);
        add(invisibleButton, BorderLayout.CENTER);
        setPreferredSize(new Dimension(270, 30)); // Tamaño del recuadro ajustado
    }
}
