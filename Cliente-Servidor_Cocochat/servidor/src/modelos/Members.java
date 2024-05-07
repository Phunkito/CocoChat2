/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author penil
 */
public class Members {
    private int id_members;
    private int id_user;
    private int id_group;
    
    // Constructor
    public Members(int id_members, int id_user, int id_group) {
        this.id_members = id_members;
        this.id_user = id_user;
        this.id_group = id_group;
    }

    // Getters y Setters
    public int getId_members() {
        return id_members;
    }

    public void setId_members(int id_members) {
        this.id_members = id_members;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }
}
