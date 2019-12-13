package dad.connectionfactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    public static final String URL = "jdbc:mysql://localhost:3306/pizza_db";
    public static final String USER = "root";
    public static final String PASS = "magogo";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
}
