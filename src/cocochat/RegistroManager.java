/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cocochat;

/**
 *
 * @author alan2
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistroManager {
    private Connection connection;

    public RegistroManager() {
        // Conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/CocoChat";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                connection = DriverManager.getConnection(url, "root", "");
            } catch (SQLException ex) {
                Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean crearUsuario(String usuario, String contraseña) {
        // Verificar que la contraseña tenga al menos 8 caracteres
        if (contraseña.length() < 8) {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 8 caracteres.");
            return false;
        }

        // Verificar si ya existe un usuario con ese nombre
        if (existeUsuario(usuario)) {
            JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nombre.");
            return false;
        }

        // Encriptar la contraseña antes de almacenarla en la base de datos
        String contraseñaEncriptada = encriptarContraseña(contraseña);

        String consulta = "INSERT INTO usuario (username, password) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(consulta)) {
            stmt.setString(1, usuario);
            stmt.setString(2, contraseñaEncriptada);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0; // Retorna true si al menos una fila fue insertada correctamente
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private boolean existeUsuario(String usuario) {
        String consulta = "SELECT COUNT(*) FROM usuario WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(consulta)) {
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Retorna true si existe al menos un usuario con ese nombre
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Método para encriptar la contraseña utilizando el algoritmo SHA-256
    private String encriptarContraseña(String contraseña) {
        try {
            // Crear una instancia del algoritmo de hash SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Aplicar el algoritmo de hash a la contraseña
            byte[] hash = digest.digest(contraseña.getBytes());

            // Convertir el hash en una representación hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // Retornar la contraseña encriptada como una cadena hexadecimal
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // Manejo de error en caso de algoritmo no encontrado
        }
    }
}
