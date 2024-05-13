/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cocochat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author SvrusLink
 */
public class serverLogsInterfaz extends JFrame {

    String logs = new String();
    JLabel titulo = new JLabel();
    JButton botonPrint = new JButton();
    JTextField cuadro = new JTextField();

    public serverLogsInterfaz() {
        super();
        config();
        JPanel panelLogs = new JPanel(new BorderLayout());
        add(panelLogs);
        setVisible(true);
    }

    private void accionPrint() {
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(cuadro.getText());
            System.out.println("Archivo creado exitosamente!");
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo: " + e);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Hubo una excepcion: " + e);
        } catch (IOException e) {
            System.out.println("Hubo un error: " + e);
        } catch (SecurityException e) {
            System.out.println("Hubo un error de seguridad: " + e);
        }
    }

    private void config() //Se manda a llamar en el constructor para hacer la configuracion inicial del formulario
    {
        this.setSize(1280, 720);
        this.setTitle("Impresion de Todos logs");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.decode("#293048")); //7990F8
        this.setLayout(null);
        this.setResizable(false);

        botonPrint.setSize(450, 80);
        botonPrint.setLocation(70, 550);
        botonPrint.setFont(new Font("Rockwell", Font.PLAIN, 30));
        botonPrint.setText("Imprime Todos los Logs");
        botonPrint.setBackground(Color.decode("#A9D0FF"));
        botonPrint.addActionListener((ActionEvent e) -> {
            accionPrint();
        });
        this.add(botonPrint);

        titulo.setSize(500, 90);
        titulo.setFont(new Font("Rockwell", Font.BOLD, 70));
        titulo.setText("CocoChat");
        titulo.setForeground(Color.white);
        titulo.setLocation(80, 25);
        this.add(titulo);

        cuadro.setSize(1130, 370);
        cuadro.setLocation(70, 135);
        cuadro.setEditable(false);
        cuadro.setText(logs);
        this.add(cuadro);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new serverLogsInterfaz());
    }
}
