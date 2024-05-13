/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

public class Usuario {
    private int id;
    private String username;
    private String password;
    private boolean online; //esto no se inserta a la bd, se envia al server para saber quienes estan activos

    // Constructor
    public Usuario(int id, String username, String password, boolean online) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.online = online;
    }
    
    // Getters y Setters
    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}