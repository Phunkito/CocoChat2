package cocochat;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class MenuUsuarioInterfaz extends JFrame {
    private JPanel panelUsuarios;
    private JPanel panelAmigos;
    private JPanel panelGrupos;
    private PrintWriter salidaServidor;
    private BufferedReader entradaServidor;
    private boolean ejecutando = true;
    private DefaultListModel<String> listModel;

    // Constructor para configurar la interfaz
    public MenuUsuarioInterfaz() {

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

        JButton botonGrupo = new JButton("Crear Grupo");
        botonGrupo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(this, "Cerrar Sesión");
                
                new CreacionGruposInterfaz();
                dispose();
            }

            //
        });
        
        // Crear botones para solicitudes y cerrar sesión
        JButton botonRequest = new JButton("Solicitudes");
        botonRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(this, "Solicitudes");
                new InvitacionesInterfaz();
            }

            //
        });

        JButton botonLogOut = new JButton("Cerrar Sesion");
        botonLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(this, "Cerrar Sesión");
                
            }
            
            //
            
        });

        // Crear el panel superior usando un diseño de cuadrícula
        JPanel panelSuperior = new JPanel(new GridLayout(1, 3, 10, 10));
        panelSuperior.add(titulo);
        panelSuperior.add(botonGrupo);
        panelSuperior.add(botonRequest);
        panelSuperior.add(botonLogOut);        

        // Crear paneles vacíos que se llenarán con los datos recibidos
        panelUsuarios = crearPanelConLista("Usuarios", new String[]{});
        panelAmigos = crearPanelConLista("Amigos", new String[]{});
        panelGrupos = crearPanelConLista("Grupos", new String[]{});

        // Usar un `JSplitPane` para dividir la ventana en tres partes verticales
        JSplitPane splitPanePrincipal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelUsuarios, panelAmigos);
        splitPanePrincipal.setResizeWeight(0.33);
        JSplitPane splitPaneCompleto = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPanePrincipal, panelGrupos);
        splitPaneCompleto.setResizeWeight(0.66);

        // Crear un panel principal usando `BorderLayout`
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(splitPaneCompleto, BorderLayout.CENTER);

        add(panelPrincipal);
        setVisible(true);

        // Cargar datos desde el servidor y establecer el socket para enviar mensajes
        cargarDatosDesdeServidor();
    }

    // Método para crear un panel con una lista y botones con íconos condicionales
    private JPanel crearPanelConLista(String titulo, String[] elementos) {
                listModel = new DefaultListModel<>();

        String[] listData = {"Elemento 1", "Elemento 2", "Elemento 3", "Elemento 4"};
        elementos = listData;

        // Crear el componente JList
        JList<String> list = new JList<>(elementos);
        
        // Asegurarse de que se puedan seleccionar múltiples elementos
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Crear un panel de desplazamiento para la lista
        JScrollPane scrollPane = new JScrollPane(list);
        
        //Crear el panel final
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(titulo));

        /*
        JPanel panelLista = new JPanel();
        panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));

        // Ejemplo de ícono: cambiar la ruta a la imagen deseada
        Icon icono = new ImageIcon("ruta/al/icono.png");

        for (String elemento : listData) {
            // Panel para cada elemento que contiene un botón y una etiqueta opcional
            JPanel panelElemento = new JPanel(new BorderLayout());
            JButton botonElemento = new JButton(elemento);

            // Añadir la acción del botón para enviar mensajes al servidor
            botonElemento.addActionListener(e -> enviarMensajeServidor("Botón presionado: " + elemento));
            panelElemento.add(botonElemento, BorderLayout.CENTER);

            // Condición para mostrar el ícono
            if (debeMostrarIcono(elemento)) {
                JLabel etiquetaIcono = new JLabel(icono);
                panelElemento.add(etiquetaIcono, BorderLayout.WEST);
            }

            panelLista.add(panelElemento);
        }
        */

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
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
        SwingUtilities.invokeLater(() -> new MenuUsuarioInterfaz());
    }
}
