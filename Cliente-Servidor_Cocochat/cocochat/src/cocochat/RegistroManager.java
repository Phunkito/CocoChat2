/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cocochat;

/**
 *
 * @author alan2
 */
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class RegistroManager {
    private Connection connection;
    public String mensaje;

    public RegistroManager() {
        // Conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/CocoBase";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                connection = DriverManager.getConnection(url, "root", "");
            } catch (SQLException ex) {
                Logger.getLogger(RegistroManager.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistroManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean crearUsuario(String usuario, String contraseña, String securityWord) {
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

        // Encriptar la contraseña y la palabra de seguridad antes de almacenarlas en la base de datos
        String contraseñaEncriptada = encriptarTexto(contraseña);
        String securityWordEncriptada = encriptarTexto(securityWord);

        String consulta = "INSERT INTO users (username, password, security_word) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(consulta)) {
            stmt.setString(1, usuario);
            stmt.setString(2, contraseña);
            stmt.setString(3, securityWord);
            int filasAfectadas = stmt.executeUpdate();
            mensaje = usuario + "," + contraseña + "," + securityWord;
            return filasAfectadas > 0; // Retorna true si al menos una fila fue insertada correctamente
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private boolean existeUsuario(String usuario) {
        String consulta = "SELECT COUNT(*) FROM users WHERE username = ?";
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

    // Método para encriptar la contraseña y la palabra de seguridad utilizando PBKDF2 con HMAC-SHA256
    private String encriptarTexto(String texto) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            // Configurar los parámetros para la derivación de claves
            PBEKeySpec spec = new PBEKeySpec(texto.toCharArray(), salt, 65536, 256);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            // Derivar la clave
            byte[] hash = factory.generateSecret(spec).getEncoded();

            // Concatenar el salt con el hash para almacenar ambos en la base de datos
            byte[] combined = new byte[salt.length + hash.length];
            System.arraycopy(salt, 0, combined, 0, salt.length);
            System.arraycopy(hash, 0, combined, salt.length, hash.length);

            // Retornar el texto encriptado como una cadena Base64
            return Base64.getEncoder().encodeToString(combined);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return null; // Manejo de error en caso de algoritmo no encontrado
        }
    }
}

