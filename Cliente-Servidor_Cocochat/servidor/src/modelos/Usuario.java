/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

public class Usuario {
    private int id;
    private String username;
    private String password;
    private String security_word;

    // Constructor
    public Usuario(int id, String username, String password, String Security_word) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.security_word = Security_word;
    }

    // Getters y Setters
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
    
    public String getSecurity_word() {
        return security_word;
    }

    public void setSecurity_word(String security_word) {
        this.security_word = security_word;
    }
}

