/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author penil
 */
public class Grupo {
    private int id_group;
    private int id_admin; //user que es admin
    private String title;
    
    // Constructor
    public Grupo(int id_group, int id_admin, String title) {
        this.id_group = id_group;
        this.id_admin = id_admin;
        this.title = title;
    }

    // Getters y Setters
    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
