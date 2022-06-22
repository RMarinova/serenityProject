package REST.stepdefinitions;

import REST.stepsLibrary.RestStepLibrary;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class CRUD {

    @Steps
    RestStepLibrary restStepLibrary;


    @When("I perform a {string} request to {string} using a {string}")
    public void iPerformARequestTo(String operation, String path, String userModelString) {

        restStepLibrary.performRestRequest(operation, path, userModelString);

    }

    @Then("I should receive a user with the specific id")
    @Then("I should receive all users")
    public void iShouldReceiveAllUsers() {

        restStepLibrary.getUsers();
    }

    @Then("I should receive status code {string} with a message {string}")
    @And("Status code {string} with a message {string}")
    public void statusCodeWithAMessage(String statusCode, String statusMessage) {

        restStepLibrary.assertStatusCodeAndMessage(statusCode, statusMessage);

    }

    @And("I use the following details to update the user:")
    @When("I use the following details with lombok")
    public void iUseTheFollowingDetails(DataTable table) {

        restStepLibrary.setUpUserDetailsWithTable(table);

    }

    @Given("A user is registered")
    public void aUserIsRegistered() {

        restStepLibrary.aDefaultUserIsRegistered();

    }
}
