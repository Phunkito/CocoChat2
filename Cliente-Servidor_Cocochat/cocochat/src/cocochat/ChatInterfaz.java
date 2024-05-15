/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cocochat;

/**
 *
 * @author alan2
 */
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author valer
 */
public class ChatInterfaz extends JFrame {

    private JButton btnEnviar;
    private JButton btnRegresar;
    private JLabel NombreChat;
    private JScrollPane jScrollPane1;
    private JTextArea txtTexto;
    private JTextField txtTextoEnviar;

    public ChatInterfaz() {
        setSize(800, 600);
        setTitle("Chat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jScrollPane1 = new JScrollPane();
        txtTexto = new JTextArea();
        btnEnviar = new JButton();
        btnRegresar = new JButton();
        NombreChat = new JLabel("Nombre del chat");
        txtTextoEnviar = new JTextField();

        txtTexto.setColumns(20);
        txtTexto.setRows(13);
        jScrollPane1.setViewportView(txtTexto);
        NombreChat.setFont(new Font("Arial", Font.BOLD, 20));
        NombreChat.setHorizontalAlignment(SwingConstants.CENTER);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Codigo para enviar mensaje
                    }
                }
        );

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código regresar al menu de usuarios
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnRegresar)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(NombreChat))
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(txtTextoEnviar)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnEnviar, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(NombreChat)
                                        .addComponent(btnRegresar))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE) // Cambio aquí
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtTextoEnviar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE) // Cambio aquí
                                        .addComponent(btnEnviar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)) // Cambio aquí
                                .addContainerGap())
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ChatInterfaz().setVisible(true);
        });
    }
}