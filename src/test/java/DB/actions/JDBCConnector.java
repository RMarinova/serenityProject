package common;

import dataProviders.ConfigReader;
import java.sql.*;

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
