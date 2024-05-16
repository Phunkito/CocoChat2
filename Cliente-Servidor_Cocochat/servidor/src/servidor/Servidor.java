/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package servidor;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rsv15
 */


public class Servidor {
    private static HashMap<String, Socket> clientesConectados = new HashMap<>();
     private Connection connection;

    
    public void registroManager(){
        String url = "jdbc:mysql://localhost:3306/CocoBase";
        
        try{
            connection = DriverManager.getConnection(url, "root", "");
        }catch(SQLException ex){
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(1234);
            System.out.println("Esperando conexiones...");

            while (true) {
                Socket cliente = ss.accept();
                System.out.println("Cliente conectado: " + cliente);

                // Aquí asignamos un identificador único al cliente
                String idCliente = "Cliente" + System.currentTimeMillis();
                clientesConectados.put(idCliente, cliente);

                Thread t = new Thread(new ManejadorCliente(cliente, idCliente));
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ManejadorCliente implements Runnable {
        private Socket cliente;
        private String idCliente;

        public ManejadorCliente(Socket cliente, String idCliente) {
            this.cliente = cliente;
            this.idCliente = idCliente;
        }

        @Override
        public void run() {
            try {
                byte[] arreglo = new byte[20];
                cliente.getInputStream().read(arreglo);
                String obj = new String(arreglo);
                System.out.println("Mensaje recibido de " + idCliente + ": " + obj);
                
                if(obj.contains("#"))
                {
                    System.out.println(obj); //si tiene un # lo envia a la vista de mensajes
                }else
                {
                    //Se ejecuta una query
                    
                }

                // Aquí puedes implementar la lógica para procesar y responder al cliente específico
                // Por ejemplo, enviar un mensaje a un cliente en particular
                if (idCliente.equals("Cliente123")) {
                    Socket clienteDestino = clientesConectados.get("Cliente123");
                    OutputStream outputStream = clienteDestino.getOutputStream();
                    outputStream.write("Mensaje para Cliente123".getBytes());
                }
                
                

                cliente.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

