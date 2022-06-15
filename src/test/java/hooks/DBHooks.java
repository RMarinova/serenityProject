package common.hooks;

import common.DBActions;
import common.JDBCConnector;
import io.cucumber.java.After;

import java.sql.SQLException;

public class DBHooks {

    private DBActions dbActions;
    private JDBCConnector jdbcConnector;

    {
        try {
            jdbcConnector = new JDBCConnector();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public DBHooks(){
        dbActions = new DBActions();
    }
    @After("@DB")
    public void closeConnection() throws SQLException {
        jdbcConnector.closeConnection();
    }

    @After("@DeleteUser")
    public void deleteUser() throws SQLException {
        dbActions.deleteUser();
    }
}
