package cms;

import cms.util.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class TestDB {
    public static void main(String[] args) throws SQLException {
        Connection c = DBConnection.getConnection();
        if (c != null) {
            System.out.println("✅ Connected to DB");
            try { c.close(); } catch (SQLException ignored) {}
        } else {
            System.out.println("❌ Connection is null — check DBConnection and JDBC driver");
        }
    }
}
