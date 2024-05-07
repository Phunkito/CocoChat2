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
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;

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
    public boolean cambiarContraseña(String usuario, String nuevaContraseña, String confirmarContraseña, String securityWord) throws InvalidKeySpecException {
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

        // Verificar si la palabra de seguridad coincide
        if (!verificarPalabraSeguridad(usuario, securityWord)) {
            JOptionPane.showMessageDialog(null, "La palabra de seguridad es incorrecta.");
            return false;
        }

        // Encriptar la nueva contraseña antes de almacenarla en la base de datos
        String contraseñaEncriptada = encriptarContraseña(nuevaContraseña);

        // Actualizar la contraseña en la base de datos
        String consulta = "UPDATE usuario SET password = ? WHERE username = ?";
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
        String consulta = "SELECT * FROM usuario WHERE username = ?";
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

    // Método para encriptar la contraseña utilizando PBKDF2 con HMAC-SHA256
    private String encriptarContraseña(String contraseña) throws InvalidKeySpecException {
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            // Configurar los parámetros para la derivación de claves
            PBEKeySpec spec = new PBEKeySpec(contraseña.toCharArray(), salt, 65536, 256);
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
            JOptionPane.showMessageDialog(null, "Error al encriptar la contraseña.");
            e.printStackTrace();
            return null;
        }
    }

    // Método para desencriptar la palabra de seguridad utilizando PBKDF2 con HMAC-SHA256
    private String desencriptarPalabraSeguridad(String usuario, String securityWord) {
        String consulta = "SELECT security_word FROM usuario WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(consulta)) {
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String palabraSeguridadEncriptada = rs.getString("security_word");
                byte[] combined = Base64.getDecoder().decode(palabraSeguridadEncriptada);

                // Extraer el salt y el hash de la palabra de seguridad encriptada
                byte[] salt = new byte[16];
                byte[] hash = new byte[combined.length - 16];
                System.arraycopy(combined, 0, salt, 0, salt.length);
                System.arraycopy(combined, salt.length, hash, 0, hash.length);

                // Configurar los parámetros para la derivación de claves
                PBEKeySpec spec = new PBEKeySpec(securityWord.toCharArray(), salt, 65536, 256);
                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

                // Derivar la clave y compararla con el hash almacenado
                byte[] testHash = factory.generateSecret(spec).getEncoded();
                if (MessageDigest.isEqual(hash, testHash)) {
                    return securityWord;
                }
            }
            JOptionPane.showMessageDialog(null, "Error al desencriptar la palabra de seguridad.");
            return null;
        } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException ex) {
            JOptionPane.showMessageDialog(null, "Error al desencriptar la palabra de seguridad.");
            ex.printStackTrace();
            return null;
        }
    }

    // Método para verificar si la palabra de seguridad coincide con la del usuario
    private boolean verificarPalabraSeguridad(String usuario, String securityWord) {
        String palabraSeguridadDesencriptada = desencriptarPalabraSeguridad(usuario, securityWord);
        return palabraSeguridadDesencriptada != null && palabraSeguridadDesencriptada.equals(securityWord);
    }
}
