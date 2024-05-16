/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Querys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

public class Querys {

    private Connection conn;

    public Querys(Connection conn) {
        String url = "jdbc:mysql://localhost:3306/CocoBase";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                conn = DriverManager.getConnection(url, "root", "");
            } catch (SQLException ex) {
                Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Querys.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertFriend(int userId, int friendId) {
        String sql = "INSERT INTO friends (Id_user, Id_user2) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, friendId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFriend(int userId, int friendId) {
        String sql = "DELETE FROM friends WHERE (Id_user = ? AND Id_user2 = ?) OR (Id_user = ? AND Id_user2 = ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, friendId);
            stmt.setInt(3, friendId);
            stmt.setInt(4, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertGroup(int adminId, String title) {
        String sql = "INSERT INTO groups (Id_admin, title) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, adminId);
            stmt.setString(2, title);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGroup(int groupId) {
        String sql = "DELETE FROM groups WHERE Id_group = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, groupId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void leaveGroup(int userId, int groupId) {
        String sql = "DELETE FROM members WHERE Id_user = ? AND Id_group = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, groupId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteChatWithUser(int userId, int friendId) {
        String sql = "DELETE FROM message WHERE (Id_user = ? AND Id_group = 0) OR (Id_user = ? AND Id_user = 0)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, friendId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteChatFromGroup(int groupId) {
        String sql = "DELETE FROM message WHERE Id_group = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, groupId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rejectFriendRequest(int requestId) {
        String sql = "UPDATE request SET status = 'rejected' WHERE Id_request = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, requestId);
            stmt.executeUpdate();
            System.out.println("Solicitud de amistad rechazada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void acceptFriendRequest(int requestId) {
        String sql = "UPDATE request SET status = 'accepted' WHERE Id_request = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, requestId);
            stmt.executeUpdate();
            System.out.println("Solicitud de amistad aceptada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showFriends(int userId) {
        String sql = "SELECT u.username FROM users u JOIN friends f ON u.Id_user = f.Id_user2 WHERE f.Id_user = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Amigos:");
            while (rs.next()) {
                System.out.println(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showGroups(int userId) {
        String sql = "SELECT g.title FROM groups g JOIN members m ON g.Id_group = m.Id_group WHERE m.Id_user = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Grupos:");
            while (rs.next()) {
                System.out.println(rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showFriendRequests(int userId) {
        String sql = "SELECT u.username FROM users u JOIN request r ON u.Id_user = r.Id_user_sended WHERE r.Id_user_recieved = ? AND r.status = 'pending'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Solicitudes de amistad pendientes:");
            while (rs.next()) {
                System.out.println(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void enviarMensajeUsuarioAUsuario(int senderId, int receiverId, String mensaje) {
        // Tu código para conectarte a la base de datos
        String query = "INSERT INTO message (Id_user, text, Id_user_received) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CocoBase", "root", ""); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, senderId);
            pstmt.setString(2, mensaje);
            pstmt.setInt(3, receiverId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void enviarMensajeAGrupo(int senderId, int groupId, String mensaje) {
        // Tu código para conectarte a la base de datos
        String query = "INSERT INTO message (Id_user, text, Id_group) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CocoBase", "root", ""); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, senderId);
            pstmt.setString(2, mensaje);
            pstmt.setInt(3, groupId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
   public void obtenerMensajesEntreUsuarios(int userId1, int userId2) {
        // Tu código para conectarte a la base de datos
        String query = "SELECT * FROM message WHERE (Id_user = ? AND Id_user_received = ?) OR (Id_user = ? AND Id_user_received = ?) ORDER BY Id_message";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CocoBase", "root", ""); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId1);
            pstmt.setInt(2, userId2);
            pstmt.setInt(3, userId2);
            pstmt.setInt(4, userId1);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // Procesar cada mensaje
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void obtenerMensajesGrupo(int groupId) {
        // Tu código para conectarte a la base de datos
        String query = "SELECT * FROM message WHERE Id_group = ? ORDER BY Id_message";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/CocoBase", "root", ""); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, groupId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // Procesar cada mensaje
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}