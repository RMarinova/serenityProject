package DB.utils;

import TestData.DefaultUser;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.Serenity;
import org.modelmapper.ModelMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBActions {

    private ResultSet resultSet;
    private UserEntity userEntity = new UserEntity();
    private DefaultUser defaultUser = new DefaultUser();
    private DBQueries dbQueries = new DBQueries();
    private List<UserEntity> usersList = new ArrayList<>();
    private JDBCConnector jdbcConnector;
    private String email;
    private String firstName;
    private String lastName;

    {
        try {
            jdbcConnector = new JDBCConnector();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void getAllUsers() throws SQLException {

        resultSet = jdbcConnector.getStatement().executeQuery(dbQueries.selectAll);

        settingUpUser(resultSet, userEntity, usersList);
    }

    public void creatingNewUser(DataTable table) throws SQLException {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        ModelMapper modelMapper = new ModelMapper();
        UserEntity user = modelMapper.map(data.get(0), UserEntity.class);

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


        this.email = user.getEmail();

        Serenity.environmentVariables().setProperty("email", email);

        jdbcConnector.getStatement().executeUpdate(query);

    }

    public UserEntity settingUpUserWithEmail() throws SQLException {
        resultSet = jdbcConnector.getStatement().executeQuery(dbQueries.selectWithEmail(email));
        while (resultSet.next()) {
            userEntity.setTitle(resultSet.getString("title"));
            userEntity.setFirst_name(resultSet.getString("first_name"));
            userEntity.setSir_name(resultSet.getString("sir_name"));
            userEntity.setEmail(resultSet.getString("email"));
            userEntity.setPassword(resultSet.getString("password"));
            userEntity.setCountry(resultSet.getString("country"));
            userEntity.setCity(resultSet.getString("city"));
            userEntity.setIs_admin(resultSet.getString("is_admin"));
            userEntity.setId(resultSet.getString("id"));

            usersList.add(userEntity);
            System.out.println(userEntity);

        }
        return userEntity;
    }

    public String returningEmail() {
        return email;
    }

    public String returningFirstName() {
        return firstName;
    }

    public String returningLastName() {
        return lastName;
    }

    public List<UserEntity> returnUserModelList() {
        return usersList;
    }

    public void deleteUser() throws SQLException {

        jdbcConnector.getStatement().executeUpdate(dbQueries.deleteUser(Serenity.environmentVariables().getProperty("email")));

    }

    public void creatingNewDefaultUser() throws SQLException {

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

        this.email = user.getEmail();
        Serenity.environmentVariables().setProperty("email", email);

        jdbcConnector.getStatement().executeUpdate(query);
    }

    public void updateUserDetails(String firstName, String lastName) throws SQLException {
        this.firstName = firstName;
        this.lastName = lastName;
        String query = dbQueries.updateFirstAndLastName(firstName, lastName, email);
        jdbcConnector.getStatement().executeUpdate(query);
    }


    private void settingUpUser(ResultSet result, UserEntity user, List<UserEntity> userEntityList) throws SQLException {

        while (result.next()) {
            user.setTitle(result.getString("title"));
            user.setFirst_name(result.getString("first_name"));
            user.setSir_name(result.getString("sir_name"));
            user.setEmail(result.getString("email"));
            user.setPassword(result.getString("password"));
            user.setCountry(result.getString("country"));
            user.setCity(result.getString("city"));
            user.setIs_admin(result.getString("is_admin"));
            user.setId(result.getString("id"));

            userEntityList.add(user);
            System.out.println(user);
        }
    }


}
