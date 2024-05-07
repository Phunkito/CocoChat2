/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author penil
 */
public class Friend {
    private int id_friend;
    private int id_user;
    private int id_user2;
    
    // Constructor
    public Friend(int id_friend, int id_user, int id_user2) {
        this.id_friend = id_friend;
        this.id_user = id_user;
        this.id_user2 = id_user2;
    }

    // Getters y Setters
    public int getId_friend() {
        return id_friend;
    }

    public void setId_friend(int id_friend) {
        this.id_friend = id_friend; 
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_user2() {
        return id_user2;
    }

    public void setId_user2(int id_user2) {
        this.id_user2 = id_user2;
    }
}
