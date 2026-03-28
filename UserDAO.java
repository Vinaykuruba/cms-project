package cms.dao;

import cms.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    /**
     * Validate when passwords stored as plain text in DB (not recommended for production).
     */
    public boolean validateUser(String username, String password) {
        String sql = "SELECT 1 FROM users WHERE username = ? AND password = ? LIMIT 1";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, username.trim());
            pst.setString(2, password); // if you normalized in DB, normalize here too
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            // Print stacktrace so you can see DB errors in console
            e.printStackTrace();
            return false;
        }
    }

    /*
    // If your DB stores bcrypt hashes, use something like this (requires jBCrypt):
    import org.mindrot.jbcrypt.BCrypt;
    public boolean validateUser(String username, String passwordPlain) {
        String sql = "SELECT password FROM users WHERE username = ? LIMIT 1";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, username.trim());
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password");
                    return BCrypt.checkpw(passwordPlain, storedHash);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    */
}
