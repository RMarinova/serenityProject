package DB.stepdefinitions;

import DB.stepsLibrary.DBStepsLibrary;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.sql.SQLException;

public class DataBaseSteps {

    @Steps
    DBStepsLibrary dbStepsLibrary;


    @When("I get all existing Users information")
    public void iGetAllExistingUsersInformation() throws SQLException {

        dbStepsLibrary.getAllExistingUsersInformation();

    }

    @When("I create a new user with the following information:")
    public void iCreateANewUserWithTheFollowingInformation(DataTable table) throws SQLException {

        dbStepsLibrary.createNewUserWithDataTable(table);

    }

    @Then("I can receive the user using his email address")
    public void iCanSelectTheUserUsingHisEmailAddress() throws SQLException {

        dbStepsLibrary.selectUserByEmail();

    }

    @Given("A user is registered through the DataBase")
    public void aUserIsRegisteredThroughTheDataBase() throws SQLException {

        dbStepsLibrary.createNewDefaultUser();

    }

    @When("I update the user's first and last name with: {string}, {string}")
    public void iUpdateTheUserSFirstAndLastNameWith(String firstName, String lastName) throws SQLException {

        dbStepsLibrary.updateUserDetailsInDB(firstName, lastName);

    }

    @Then("all the users are displayed")
    public void allTheUsersAreDisplayed() {

        dbStepsLibrary.assertAllUsersAreDisplayed();
    }

    @Then("I should receive the user's updated information")
    public void iShouldReceiveTheUserSUpdatedInformation() throws SQLException {

        dbStepsLibrary.getUsersUpdatedInformation();

    }

    @When("I Delete the user")
    public void iDeleteTheUser() throws SQLException {

        dbStepsLibrary.deleteUser();

    }

    @Then("I should not be able to select the user")
    public void iShouldNotBeAbleToSelectTheUser() throws SQLException {

        dbStepsLibrary.assertUserIsNull();

    }
}
