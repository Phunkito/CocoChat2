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

public class InvitacionesInterfaz extends JFrame {

    public InvitacionesInterfaz() {
        super();
        config();
    }

    private void config() {
        // Panel principal con color de fondo
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.decode("#7990f8"));
        mainPanel.setLayout(new BorderLayout());
        this.add(mainPanel);

        // Crear datos de ejemplo
        String[] solicitudes = {"Solicitud 1", "Solicitud 2", "Solicitud 3", "Solicitud 4"};

        // Crear el modelo de lista con datos de ejemplo
        DefaultListModel<JPanel> listModel = new DefaultListModel<>();
        for (String solicitud : solicitudes) {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            JLabel label = new JLabel(solicitud);
            panel.add(label, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel(new GridLayout(1, 2)); // Panel para botones de aceptar y eliminar
            JButton aceptarButton = new JButton("Aceptar");
            aceptarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(InvitacionesInterfaz.this, "Solicitud aceptada: " + solicitud);
                }
            });
            buttonPanel.add(aceptarButton);

            JButton eliminarButton = new JButton("Eliminar");
            eliminarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(InvitacionesInterfaz.this, "Solicitud eliminada: " + solicitud);
                }
            });
            buttonPanel.add(eliminarButton);

            panel.add(buttonPanel, BorderLayout.EAST);

            listModel.addElement(panel);
        }

        // Crear la lista con el modelo personalizado
        JList<JPanel> list = new JList<>(listModel);
        list.setCellRenderer(new PanelListCellRenderer());
        JScrollPane scrollPane = new JScrollPane(list);

        // Agregar la lista al centro del panel principal
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Botón de regreso en la esquina inferior derecha
        JButton botonRegresar = new JButton("Regresar");
        botonRegresar.setBackground(Color.WHITE);
        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(InvitacionesInterfaz.this, "Regresando...");
                // Código para regresar
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
    }

    public static void main(String[] args) {
        InvitacionesInterfaz interfaz = new InvitacionesInterfaz();
        interfaz.setVisible(true);
    }

    // Renderer personalizado para mostrar los paneles en la lista
    private class PanelListCellRenderer implements ListCellRenderer<JPanel> {
        @Override
        public Component getListCellRendererComponent(JList<? extends JPanel> list, JPanel value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            return value;
        }
    }
}
