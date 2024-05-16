/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cocochat;

/**
 *
 * @author valer
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServer {

    static ArrayList<String> userNames = new ArrayList<String>();

    static ArrayList<PrintWriter> printWriters = new ArrayList<PrintWriter>();

    public static void main(String[] args) throws Exception {

        // TODO Auto-generated method stub
        System.out.println("Servidor iniciado");

        ServerSocket ss = new ServerSocket(9806);
        

        while(true) {
            
            //imprimir();
            
            Socket soc = ss.accept();
            
            imprimir();

            System.out.println("Cliente conectado" + userNames);

            ConversationHandler handler = new ConversationHandler(soc);

            handler.start();

        }

    }
    
    public static void imprimir(){
        RegistroManager instancia = new RegistroManager();
        String mensaje2 = instancia.mensaje;
        
        System.out.println("Nuevo usuario creado: " + mensaje2);
        
    }

}

class ConversationHandler extends Thread {

    Socket socket;

    BufferedReader in;

    PrintWriter out;

    String name;

    PrintWriter pw;

    static FileWriter fw;

    static BufferedWriter bw;

    public ConversationHandler(Socket socket) throws IOException {

        this.socket = socket;

        fw = new FileWriter("C:\\Users\\sassi\\OneDrive\\Escritorio\\ChatServer-Logs.txt", true);

        bw = new BufferedWriter(fw);

        pw = new PrintWriter(bw, true);

    }

    public void run() {

        try {

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream(), true);

            int count = 0;

            while (true) {

                if (count > 0) {

                    out.println("NAMEALREADYEXISTS");

                } else {

                    out.println("NAMEREQUIRED");

                }

                name = in.readLine();

                if (name == null) {

                    return;

                }

                if (!ChatServer.userNames.contains(name)) {

                    ChatServer.userNames.add(name);

                    break;

                }

                count++;

            }

            out.println("NAMEACCEPTED" + name);

            ChatServer.printWriters.add(out);

            while (true) {

                String message = in.readLine();

                if (message == null) {

                    return;

                }

                pw.println(name + ": " + message);

                for (PrintWriter writer : ChatServer.printWriters) {

                    writer.println(name + ": " + message);

                }

            }

        } catch (Exception e) {

            System.out.println(e);

        }

    }

}
