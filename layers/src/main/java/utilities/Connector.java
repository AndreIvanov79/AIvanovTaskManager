package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Connector {
    private static final Logger log = Logger.getLogger(Connector.class);
    private static Connector instance;
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/taskmanager";
    private static final String USERNAME = "aivanov";
    private static final String PASSWORD = "aivanov";

    private Connector() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException ex) {
            log.error("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Connector getInstance() throws SQLException {
        if (instance == null) {
            instance = new Connector();
        } else if (instance.getConnection().isClosed()) {
            instance = new Connector();
        }

        return instance;
    }
}
