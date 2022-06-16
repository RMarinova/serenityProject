package DB.stepdefinitions;

import DB.utils.DBActions;
import DB.utils.UserEntity;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import java.sql.SQLException;
import java.util.List;

public class DataBaseSteps {

    private DBActions dbActions;

    public DataBaseSteps() {
        dbActions = new DBActions();
    }


    @When("I get all existing Users information")
    public void iGetAllExistingUsersInformation() throws SQLException {
        dbActions.getAllUsers();

    }

    @When("I create a new user with the following information:")
    public void iCreateANewUserWithTheFollowingInformation(DataTable table) throws SQLException {
        dbActions.creatingNewUser(table);
    }

    @Then("I can receive the user using his email address")
    public void iCanSelectTheUserUsingHisEmailAddress() throws SQLException {
        UserEntity userEntity = dbActions.settingUpUserWithEmail();
        Assert.assertEquals("The user has not been created",userEntity.getEmail(), dbActions.returningEmail());
    }

    @Given("A user is registered through the DataBase")
    public void aUserIsRegisteredThroughTheDataBase() throws SQLException {

        dbActions.creatingNewDefaultUser();
    }

    @When("I update the user's first and last name with: {string}, {string}")
    public void iUpdateTheUserSFirstAndLastNameWith(String firstName, String lastName) throws SQLException {
        dbActions.updateUserDetails(firstName, lastName);
    }

    @Then("all the users are displayed")
    public void allTheUsersAreDisplayed() {
        List<UserEntity> usersList = dbActions.returnUserModelList();
        Assert.assertTrue("The test has failed! Not all users are returned!", usersList.size() > 6);
    }

    @Then("I should receive the user's updated information")
    public void iShouldReceiveTheUserSUpdatedInformation() throws SQLException {


        UserEntity userEntity = dbActions.settingUpUserWithEmail();

        Assertions.assertAll(
                () -> Assert.assertEquals("The email does not match!", userEntity.getEmail(), dbActions.returningEmail()),
                () -> Assert.assertEquals("The first name has not been updated!",userEntity.getFirst_name(), dbActions.returningFirstName()),
                () -> Assert.assertEquals("The last name has not been updated!",userEntity.getSir_name(), dbActions.returningLastName())
        );

    }

    @When("I Delete the user")
    public void iDeleteTheUser() throws SQLException {
        dbActions.deleteUser();
    }

    @Then("I should not be able to select the user")
    public void iShouldNotBeAbleToSelectTheUser() throws SQLException {
        UserEntity userEntity = dbActions.settingUpUserWithEmail();
        Assert.assertNull("The user has not been deleted!",userEntity.getEmail());
    }
}
