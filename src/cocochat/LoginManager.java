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
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LoginManager {

    private Connection connection;

    public LoginManager() {
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

    public boolean iniciarSesion(String usuario, String password) {
        // Verificar que la contraseña tenga al menos 8 caracteres

        String consulta = "SELECT * FROM usuario WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(consulta)) {
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String contraseñaEncriptada = rs.getString("password");
                // Desencriptar la contraseña almacenada en la base de datos
                if (verificarContraseña(password, contraseñaEncriptada)) {
                    return true; // La contraseña coincide
                }
            }
            return false; // No se encuentra el usuario o la contraseña no coincide
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Método para verificar la contraseña encriptada utilizando PBKDF2 con HMAC-SHA256
    private boolean verificarContraseña(String contraseña, String contraseñaEncriptada) {
        try {
            // Decodificar la contraseña encriptada desde Base64
            byte[] combined = Base64.getDecoder().decode(contraseñaEncriptada);

            // Extraer el salt y el hash de la contraseña encriptada
            byte[] salt = new byte[16];
            byte[] hash = new byte[combined.length - 16];
            System.arraycopy(combined, 0, salt, 0, salt.length);
            System.arraycopy(combined, salt.length, hash, 0, hash.length);

            // Configurar los parámetros para la derivación de claves
            PBEKeySpec spec = new PBEKeySpec(contraseña.toCharArray(), salt, 65536, 256);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            // Derivar la clave y compararla con el hash almacenado
            byte[] testHash = factory.generateSecret(spec).getEncoded();
            return Arrays.equals(hash, testHash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return false;
        }
    }
}
