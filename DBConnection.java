package cms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // change cmsdb, cmsuser and Cms@1234 to your DB name/user/password
    private static final String URL = "jdbc:mysql://localhost:3306/cmsdb"
            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "Vinaykuruba@2005";

    public static Connection getConnection() throws SQLException {
        // optional: Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
