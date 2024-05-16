package cocochat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class CreacionGruposInterfaz extends JFrame {
    private JPanel panelUsuarios;
    private JPanel panelAmigos;
    private JPanel panelGrupos;
    private PrintWriter salidaServidor;
    private BufferedReader entradaServidor;
    private boolean ejecutando = true;
    private ArrayList<MenuUsuariosConectados> allUsers = new ArrayList<MenuUsuariosConectados>();

    // Constructor para configurar la interfaz
    public CreacionGruposInterfaz() {
        allUsers.add(new MenuUsuariosConectados("Homelo Chino", true, this));
        allUsers.add(new MenuUsuariosConectados("Baymax", false, this));
        allUsers.add(new MenuUsuariosConectados("Homelo Chino", true, this));
        allUsers.add(new MenuUsuariosConectados("Baymax", false, this));
        allUsers.add(new MenuUsuariosConectados("Homelo Chino", true, this));
        allUsers.add(new MenuUsuariosConectados("Baymax", false, this));
        allUsers.add(new MenuUsuariosConectados("Homelo Chino", true, this));
        allUsers.add(new MenuUsuariosConectados("Baymax", false, this));
        allUsers.add(new MenuUsuariosConectados("Homelo Chino", true, this));
        allUsers.add(new MenuUsuariosConectados("Baymax", false, this));
        allUsers.add(new MenuUsuariosConectados("Homelo Chino", true, this));
        
        // Título de la ventana
        setTitle("Menú");

        // Establecer tamaño de la ventana
        setSize(800, 600);

        // Configurar operación de cierre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el título con estilo personalizado
        JLabel titulo = new JLabel("COCOCHAT");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Crear botones para solicitudes y cerrar sesión
        JButton botonCrear = new JButton("Crear");
        botonCrear.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Creado :P");
        });

        JButton botonReturn = new JButton("Regresa");
        botonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(this, "Cerrar Sesión");
                new MenuUsuarioInterfaz();
                dispose();
            }
            
            //
            
        });

        //Para el nombre del grupo
        JLabel labelNombre = new JLabel("Nombre:");
        labelNombre.setFont(new Font("Arial", Font.BOLD, 15));
        JTextField textFieldNombre = new JTextField();
        textFieldNombre.setPreferredSize(new Dimension(Short.MAX_VALUE, 40));

        // Crear el panel superior usando un diseño de cuadrícula
        JPanel panelSuperior = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelSuperior.add(titulo, gbc);

        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panelSuperior.add(botonCrear, gbc);

        gbc.gridx = 6;
        panelSuperior.add(botonReturn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelSuperior.add(labelNombre, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 6;
        panelSuperior.add(textFieldNombre, gbc);

        // Crear paneles vacíos que se llenarán con los datos recibidos
        panelUsuarios = crearPanelConLista("Usuarios");

        // Crear un panel principal usando `BorderLayout`
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelUsuarios, BorderLayout.CENTER);

        add(panelPrincipal);
        setVisible(true);

        // Cargar datos desde el servidor y establecer el socket para enviar mensajes
        cargarDatosDesdeServidor();
    }

    // Método para crear un panel con una lista y botones con íconos condicionales
    private JPanel crearPanelConLista(String titulo) {
        JScrollPane scrollPane = new JScrollPane();
        JPanel panelfinal = new JPanel(new BorderLayout()), panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(titulo));

        // Crear un panel de desplazamiento para la lista
        for (MenuUsuariosConectados user : allUsers) {
            panel.add(user);
        }

        scrollPane.setViewportView(panel);
        panelfinal.add(scrollPane);
        return panelfinal;
    }

    // Método condicional para determinar si debe mostrarse el ícono
    private boolean debeMostrarIcono(String elemento) {
        // Aquí puedes agregar condiciones específicas para determinar si se muestra el ícono
        return elemento.equals("Juan") || elemento.equals("Laura");
    }

    // Método para actualizar el contenido de un panel con nuevos datos
    private void actualizarPanelConLista(JPanel panel, String[] elementos) {
        JPanel panelLista = (JPanel) ((JScrollPane) panel.getComponent(0)).getViewport().getView();
        panelLista.removeAll();

        // Ejemplo de ícono: cambiar la ruta a la imagen deseada
        Icon icono = new ImageIcon("ruta/al/icono.png");

        for (String elemento : elementos) {
            JPanel panelElemento = new JPanel(new BorderLayout());
            JButton botonElemento = new JButton(elemento);
            botonElemento.addActionListener(e -> enviarMensajeServidor("Botón presionado: " + elemento));
            panelElemento.add(botonElemento, BorderLayout.CENTER);

            if (debeMostrarIcono(elemento)) {
                JLabel etiquetaIcono = new JLabel(icono);
                panelElemento.add(etiquetaIcono, BorderLayout.WEST);
            }

            panelLista.add(panelElemento);
        }

        panelLista.revalidate();
        panelLista.repaint();
    }

    // Método para cargar datos desde el servidor y establecer la comunicación para enviar mensajes
    private void cargarDatosDesdeServidor() {
        String host = "localhost";
        int puerto = 12345;

        try {
            Socket socket = new Socket(host, puerto);
            salidaServidor = new PrintWriter(socket.getOutputStream(), true);
            entradaServidor = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Crear un hilo para actualizar constantemente los datos desde el servidor
            new Thread(() -> {
                while (ejecutando) {
                    try {
                        // Leer datos de cada lista desde el servidor
                        String[] usuarios = entradaServidor.readLine().split(",");
                        String[] amigos = entradaServidor.readLine().split(",");
                        String[] grupos = entradaServidor.readLine().split(",");

                        // Actualizar cada panel con los datos recibidos
                        SwingUtilities.invokeLater(() -> {
                            actualizarPanelConLista(panelUsuarios, usuarios);
                            actualizarPanelConLista(panelAmigos, amigos);
                            actualizarPanelConLista(panelGrupos, grupos);
                        });

                        // Simula una actualización periódica cada 5 segundos
                        Thread.sleep(5000);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar datos desde el servidor: " + e.getMessage());
        }
    }

    // Método para enviar mensajes al servidor
    private void enviarMensajeServidor(String mensaje) {
        if (salidaServidor != null) {
            salidaServidor.println(mensaje);
        } else {
            JOptionPane.showMessageDialog(this, "No hay conexión al servidor.");
        }
    }

    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CreacionGruposInterfaz());
    }
}
