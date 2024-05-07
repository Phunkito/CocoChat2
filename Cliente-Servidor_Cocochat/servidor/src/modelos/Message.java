/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author penil
 */
public class Message {
    private int id_message;
    private int id_user;
    private String text;
    private int id_group;
    
    // Constructor
    public Message( int id_message, int id_user, String text, int id_group) {
        this.id_message = id_message;
        this.id_user = id_user;
        this.text = text;
        this.id_group = id_group;
    }

    // Getters y Setters
    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        this.id_message = id_message;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
     public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }
    
}
