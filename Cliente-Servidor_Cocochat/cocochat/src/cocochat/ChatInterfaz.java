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
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
    private JPanel panelMensajes = new JPanel();
    // Creas la lista y los componentes
    ArrayList<ChatMessagePanel> Mensajes;

    public ChatInterfaz() {
        this.Mensajes = new ArrayList<ChatMessagePanel>();

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
        NombreChat.setFont(new Font("Arial", Font.BOLD, 20));
        NombreChat.setHorizontalAlignment(SwingConstants.CENTER);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cuando se presiona el botón, obtienes el texto del JTextField
                String texto = txtTextoEnviar.getText();

                if (texto.length() <= 500) {
                    // Creas un nuevo ChatMessagePanel con ese texto
                    ChatMessagePanel nuevoMensaje = new ChatMessagePanel("Nombre del usuario", texto);

                    // Agregas el nuevo mensaje a la lista
                    Mensajes.add(nuevoMensaje);

                    // Agregas el nuevo mensaje al panel
                    panelMensajes.add(nuevoMensaje);

                    // Agregas el panel al JScrollPane
                    jScrollPane1.setViewportView(panelMensajes);

                    //Limpiar el textfield
                    txtTextoEnviar.setText("");

                    // Refrescas la vista
                    getContentPane().revalidate();
                    getContentPane().repaint();
                } else {
                    txtTextoEnviar.setText("");
                    JOptionPane.showMessageDialog(jScrollPane1, "Lo sentimos, el mensaje no puede superar los 500 caracteres");
                }
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

        //Prototipo para agregar mensajes enviados
        //Mensajes.add(new ChatMessagePanel("Nombre del usuario", "Mensaje"));
        //Prototipo para agregar mensajes recibidos
        //Mensajes.add(new ChatMessagePanel(NombreChat.getText(), "Mensaje"));
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        panelMensajes.setLayout(new BoxLayout(panelMensajes, BoxLayout.Y_AXIS));

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnRegresar)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(NombreChat))
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE) // Cambio aquí
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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
                                        .addComponent(txtTextoEnviar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnEnviar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ChatInterfaz().setVisible(true);
        });
    }
}
