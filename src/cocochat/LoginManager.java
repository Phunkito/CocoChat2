/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cocochat;

/**
 *
 * @author alan2
 */
import java.sql.*;
import java.util.logging.Level;
import javax.swing.*;
import java.util.logging.Logger;

public class LoginManager {

    private Connection connection;

public LoginManager() {
    // Conexión a la base de datos
    String url = "jdbc:mysql://localhost:3306/CocoChat";

    try {
           Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            connection = DriverManager.getConnection(url ,"root","");
        } catch (SQLException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
}


public boolean iniciarSesion(String usuario, String password) {
    // Verificar que la contraseña tenga al menos 8 caracteres

    String consulta = "SELECT * FROM usuario WHERE username = ? AND password = ?";
    try (PreparedStatement stmt = connection.prepareStatement(consulta)) {
        stmt.setString(1, usuario);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        return rs.next(); // true si se encuentra el usuario, false si no
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
}
}
