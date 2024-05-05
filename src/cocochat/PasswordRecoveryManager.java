/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cocochat;

/**
 *
 * @author alan2
 */

import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordRecoveryManager {

    private Connection connection;

    public PasswordRecoveryManager() {
        // Establecer conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/CocoChat";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos.");
            ex.printStackTrace();
        }
    }

    // Método para cambiar la contraseña del usuario
    public boolean cambiarContraseña(String usuario, String nuevaContraseña, String confirmarContraseña) {
        // Verificar si la nueva contraseña cumple con los requisitos
        if (!validarContraseña(nuevaContraseña)) {
            JOptionPane.showMessageDialog(null, "La contraseña no cumple con los requisitos mínimos.");
            return false;
        }

        // Verificar si las contraseñas coinciden
        if (!nuevaContraseña.equals(confirmarContraseña)) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.");
            return false;
        }

        // Verificar si el usuario existe
        if (!usuarioExiste(usuario)) {
            JOptionPane.showMessageDialog(null, "El usuario no existe.");
            return false;
        }

        // Encriptar la nueva contraseña antes de almacenarla en la base de datos
        String contraseñaEncriptada = encriptarContraseña(nuevaContraseña);

        // Actualizar la contraseña en la base de datos
        String consulta = "UPDATE usuarios SET password = ? WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(consulta)) {
            stmt.setString(1, contraseñaEncriptada);
            stmt.setString(2, usuario);
            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(null, "Contraseña cambiada exitosamente.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo cambiar la contraseña.");
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la contraseña.");
            ex.printStackTrace();
            return false;
        }
    }

    // Método para verificar si el usuario existe en la base de datos
    private boolean usuarioExiste(String usuario) {
        String consulta = "SELECT * FROM usuarios WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(consulta)) {
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Retorna true si el usuario existe en la base de datos
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al verificar el usuario.");
            ex.printStackTrace();
            return false;
        }
    }

    // Método para validar la contraseña
    private boolean validarContraseña(String contraseña) {
        // Implementa aquí tu lógica de validación de contraseña
        return contraseña.length() >= 8; // Por ahora, solo verifica la longitud mínima
    }

    // Método para encriptar la contraseña utilizando el algoritmo SHA-256
    private String encriptarContraseña(String contraseña) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(contraseña.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null, "Error al encriptar la contraseña.");
            e.printStackTrace();
            return null;
        }
    }
}
