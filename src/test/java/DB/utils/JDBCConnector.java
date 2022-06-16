package DB.utils;


import Configuration.ConfigReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnector {

    private Connection connection;
    private ConfigReader configReader;

    public JDBCConnector() throws SQLException {

        configReader = new ConfigReader();


        connection = DriverManager.getConnection(configReader.getDBUrl(), configReader.getDBUsername(), configReader.getDBPassword());
    }

    public Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
