/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cocochat;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author SvrusLink
 */
public class MenuDetallesGrupo extends JPanel  {

    MenuDetallesGrupo(String admin, ArrayList<String> usuarios) {
        JFrame frame = new JFrame("Detalles del grupo");
        frame.setDefaultCloseOperation(3);
        frame.setLayout(new BoxLayout(frame.getContentPane(), 1));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createTitledBorder("Detalles del grupo"));
        panel.setAlignmentX(0.5F);
        JLabel lblNombreAdmin = new JLabel(admin);
        lblNombreAdmin.setAlignmentX(0.5F);
        JTextArea txtListaMiembros = new JTextArea(5, 20);
        String users = usuarios.toString().replace("[", "");
        users = users.replace("]", "");
        txtListaMiembros.setText(users.replace(", ", "\n"));
        txtListaMiembros.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtListaMiembros);
        JButton btnSalirGrupo = new JButton("Salir del grupo");
        btnSalirGrupo.setBackground(Color.RED);
        btnSalirGrupo.setAlignmentX(0.5F);
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setAlignmentX(0.5F);
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lblNombreAdmin);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(scrollPane);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnSalirGrupo);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnRegresar);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo((Component) null);
        frame.setVisible(true);
    }
}