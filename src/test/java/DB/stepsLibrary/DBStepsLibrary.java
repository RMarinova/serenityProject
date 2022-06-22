package DB.stepsLibrary;

import DB.utils.DBActions;
import DB.utils.DBConstants;
import DB.utils.UserEntity;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import java.sql.SQLException;
import java.util.List;

public class DBStepsLibrary {

    private DBActions dbActions;

    public DBStepsLibrary() {
        dbActions = new DBActions();
    }

    @Step
    public void getAllExistingUsersInformation() throws SQLException {

        dbActions.getAllUsers();

    }

    @Step
    public void createNewUserWithDataTable(DataTable table) throws SQLException {

        String email = dbActions.creatingNewUser(table);
        Serenity.setSessionVariable(DBConstants.EMAIL).to(email);

    }

    @Step
    public void selectUserByEmail() throws SQLException {

        UserEntity userEntity = dbActions.settingUpUserWithEmail(Serenity.sessionVariableCalled(DBConstants.EMAIL));
        Assert.assertEquals("The user has not been created", userEntity.getEmail(), Serenity.sessionVariableCalled(DBConstants.EMAIL));

    }

    @Step
    public void createNewDefaultUser() throws SQLException {

        String userEmail = dbActions.creatingNewDefaultUser();
        Serenity.setSessionVariable( DBConstants.EMAIL).to(userEmail);
    }

    @Step
    public void updateUserDetailsInDB(String firstName, String lastName) throws SQLException {

        dbActions.updateUserDetails(firstName, lastName, Serenity.sessionVariableCalled(DBConstants.EMAIL));
        Serenity.setSessionVariable(DBConstants.FIRST_NAME).to(firstName);
        Serenity.setSessionVariable(DBConstants.LAST_NAME).to(lastName);

    }

    @Step
    public void assertAllUsersAreDisplayed() {
        List<UserEntity> usersList = Serenity.sessionVariableCalled(DBConstants.USERS_LIST);
        Assert.assertTrue("The test has failed! Not all users are returned!", usersList.size() > 6);

    }

    @Step
    public void getUsersUpdatedInformation() throws SQLException {

        UserEntity userEntity = dbActions.settingUpUserWithEmail(Serenity.sessionVariableCalled(DBConstants.EMAIL));

        Assertions.assertAll(
                () -> Assert.assertEquals("The email does not match!", userEntity.getEmail(), Serenity.sessionVariableCalled(DBConstants.EMAIL)),
                () -> Assert.assertEquals("The first name has not been updated!", userEntity.getFirst_name(), Serenity.sessionVariableCalled(DBConstants.FIRST_NAME)),
                () -> Assert.assertEquals("The last name has not been updated!", userEntity.getSir_name(), Serenity.sessionVariableCalled(DBConstants.LAST_NAME))
        );
    }

    @Step
    public void deleteUser() throws SQLException {

        dbActions.deleteUser();

    }

    @Step
    public void assertUserIsNull() throws SQLException {

        UserEntity userEntity = dbActions.settingUpUserWithEmail(Serenity.sessionVariableCalled(DBConstants.EMAIL));
        Assert.assertNull("The user has not been deleted!", userEntity.getEmail());

    }

}
