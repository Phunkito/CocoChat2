/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cocochat;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author SvrusLink
 */

public class serverLogsInterfaz extends JFrame
{
    JLabel titulo = new JLabel();
    JButton botonPrint = new JButton();
    JTextField cuadro = new JTextField();
    
    public serverLogsInterfaz()
    {
        super();
        config();
    }
    
    private void accionPrint()
    {
        //Aqui va lo de pasar el texto al archivo
    }
    
    private void config() //Se manda a llamar en el constructor para hacer la configuracion inicial del formulario
    {
        int noob = 0;
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
        botonPrint.addActionListener
        (new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    accionPrint();
                }
            }
        );
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
        this.add(cuadro);
    }
}