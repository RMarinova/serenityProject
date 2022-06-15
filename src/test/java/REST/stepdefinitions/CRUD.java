package steps.REST;

import common.RestActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CRUD {


    private RestActions restActions;
    private String body;
    private String newBody;

    public CRUD() {
        restActions = new RestActions();
    }

    @And("I perform a {string} request to {string} with lombok")
    @Given("I perform a {string} request to {string}.")
    @When("I perform a {string} request to {string}")
    public void iPerformARequestTo(String resource, String path) {

        if (resource.equalsIgnoreCase("GET")) {
            restActions.getResource(path);
        }
        else if (resource.equalsIgnoreCase("POST")) {
            restActions.postResourceWithLombok(path,body);
        }
        else if (resource.equalsIgnoreCase("DELETE")) {
            restActions.deleteUser();
        }
        else if (resource.equalsIgnoreCase("PUT")) {
            restActions.putResource(path, newBody);
        }
    }
    @Then("I should receive a user with the specific id")
    @Then("I should receive all users")
    public void iShouldReceiveAllUsers() {

        restActions.returnBody();
    }
    @Then("I should receive status code {string} with a message {string}")
    @And("Status code {string} with a message {string}")
    public void statusCodeWithAMessage(String statusCode, String statusMessage) {
        restActions.returnStatusCode(statusCode, statusMessage);
    }

    @And("I use the following details to update the user:")
    public void iUseTheFollowingDetails(DataTable table) {

        this.newBody = restActions.fillInRegistrationWithLombok(table);


    }

    @When("I use the following details with lombok")
    public void iUseTheFollowingDetailsWithLombok(DataTable dataTable) {
        this.body = restActions.fillInRegistrationWithLombok(dataTable);

    }

    @Given("A user is registered")
    public void aUserIsRegistered() {

    restActions.creatingNewUser();
    }
}
