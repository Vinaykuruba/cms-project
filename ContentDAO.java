package cms.dao;

import cms.model.Content;
import cms.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContentDAO {

    // Add new content
    public boolean addContent(String title, String body) {
        String sql = "INSERT INTO content (title, body, created_at) VALUES (?, ?, NOW())";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, title);
            pst.setString(2, body);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Get all contents
    public List<Content> getAllContents() {
        List<Content> list = new ArrayList<>();
        String sql = "SELECT id, title, body, created_at FROM content ORDER BY id DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Content c = new Content();
                c.setId(rs.getInt("id"));
                c.setTitle(rs.getString("title"));
                c.setBody(rs.getString("body"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    // Get content by ID (used by EditContentFrame)
    public Content getContentById(int id) {
        String sql = "SELECT id, title, body, created_at FROM content WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Content c = new Content();
                    c.setId(rs.getInt("id"));
                    c.setTitle(rs.getString("title"));
                    c.setBody(rs.getString("body"));
                    c.setCreatedAt(rs.getTimestamp("created_at"));
                    return c;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null; // not found
    }

    // Update content (used by EditContentFrame)
    public boolean updateContent(int id, String title, String body) {
        String sql = "UPDATE content SET title = ?, body = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, title);
            pst.setString(2, body);
            pst.setInt(3, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Delete content (used by DeleteContentFrame if present)
    public boolean deleteContent(int id) {
        String sql = "DELETE FROM content WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
