package DB.utils;

import TestData.DefaultUser;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.Serenity;
import org.modelmapper.ModelMapper;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBActions {


    private DBQueries dbQueries = new DBQueries();
    private JDBCConnector jdbcConnector;

    {
        try {
            jdbcConnector = new JDBCConnector();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void getAllUsers() throws SQLException {

        ResultSet resultSet = jdbcConnector.getStatement().executeQuery(dbQueries.selectAll);
        UserEntity userEntity = new UserEntity();
        settingUpUser(resultSet, userEntity);

    }

    public String creatingNewUser(DataTable table) throws SQLException {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        ModelMapper modelMapper = new ModelMapper();
        UserEntity user = null;
        for (int i = 0; i < data.size(); i++) {
            user = modelMapper.map(data.get(0), UserEntity.class);

            String query = dbQueries.addNewUser(
                    user.getId(),
                    user.getIs_admin(),
                    user.getFirst_name(),
                    user.getSir_name(),
                    user.getTitle(),
                    user.getCountry(),
                    user.getCity(),
                    user.getEmail(),
                    user.getPassword());

            jdbcConnector.getStatement().executeUpdate(query);
        }
        return user.getEmail();
    }

    public UserEntity settingUpUserWithEmail(String email) throws SQLException {

        ResultSet resultSet = jdbcConnector.getStatement().executeQuery(dbQueries.selectWithEmail(email));
        UserEntity userEntity = new UserEntity();
        settingUpUser(resultSet, userEntity);

        return Serenity.sessionVariableCalled(DBConstants.USER);
    }

    private void settingUpUser(ResultSet result, UserEntity user) throws SQLException {
        List<UserEntity> usersList = new ArrayList<>();
        while (result.next()) {
            user.setTitle(result.getString(DBConstants.USER_TITLE));
            user.setFirst_name(result.getString(DBConstants.USER_FIRST_NAME));
            user.setSir_name(result.getString(DBConstants.USER_SIR_NAME));
            user.setEmail(result.getString(DBConstants.USER_EMAIL));
            user.setPassword(result.getString(DBConstants.USER_PASSWORD));
            user.setCountry(result.getString(DBConstants.USER_COUNTRY));
            user.setCity(result.getString(DBConstants.USER_CITY));
            user.setIs_admin(result.getString(DBConstants.USER_IS_ADMIN));
            user.setId(result.getString(DBConstants.USER_ID));

            System.out.println(user);
            usersList.add(user);
        }
        Serenity.setSessionVariable(DBConstants.USERS_LIST).to(usersList);
        Serenity.setSessionVariable(DBConstants.USER).to(user);

    }

    public String creatingNewDefaultUser() throws SQLException {

        DefaultUser defaultUser = new DefaultUser();
        UserEntity user = defaultUser.createUserWithDB();

        String query = dbQueries.addNewUser(
                user.getId(),
                user.getIs_admin(),
                user.getFirst_name(),
                user.getSir_name(),
                user.getTitle(),
                user.getCountry(),
                user.getCity(),
                user.getEmail(),
                user.getPassword());

        jdbcConnector.getStatement().executeUpdate(query);
        return user.getEmail();
    }

    public void updateUserDetails(String firstName, String lastName, String email) throws SQLException {
        String query = dbQueries.updateFirstAndLastName(firstName, lastName, email);
        jdbcConnector.getStatement().executeUpdate(query);
    }


    public void deleteUser() throws SQLException {

        jdbcConnector.getStatement().executeUpdate(dbQueries.deleteUser(Serenity.sessionVariableCalled(DBConstants.EMAIL)));

    }

}
