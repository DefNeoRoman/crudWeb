package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class InitDB {
    private static Connection connection = null;

    public static Connection getConnection() {

        try {
            if (connection == null || connection.isClosed()) {
                Properties prop = new Properties();
                InputStream inputStream = InitDB.class.getClassLoader().getResourceAsStream("application.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);

                return connection;
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

