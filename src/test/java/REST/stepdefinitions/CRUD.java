package REST.stepdefinitions;

import REST.utils.BaseRestClient;
import REST.utils.RestActions;
import REST.utils.RestConstants;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;

public class CRUD {


    private RestActions restActions;
    private BaseRestClient baseRestClient;

    public CRUD() {
        restActions = new RestActions();
        baseRestClient = new BaseRestClient();
    }

    @And("I perform a {string} request to {string} with lombok")
    @When("I perform a {string} request to {string}")
    public void iPerformARequestTo(String resource, String path) {

        if (resource.equalsIgnoreCase("GET")) {

            Response response = baseRestClient.getResponse(path);
            Serenity.setSessionVariable(RestConstants.RESPONSE).to(response);

        }
        else if (resource.equalsIgnoreCase("POST")) {
            restActions.postResourceWithLombok(path,newBody);
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

      Response response = Serenity.sessionVariableCalled(RestConstants.RESPONSE);
    }
    @Then("I should receive status code {string} with a message {string}")
    @And("Status code {string} with a message {string}")
    public void statusCodeWithAMessage(String statusCode, String statusMessage) {
        restActions.returnStatusCode(statusCode, statusMessage);
    }

    @And("I use the following details to update the user:")
    @When("I use the following details with lombok")
    public void iUseTheFollowingDetails(DataTable table) {

        this.newBody = restActions.fillInRegistrationWithLombok(table);


    }

    @Given("A user is registered")
    public void aUserIsRegistered() {

    restActions.creatingNewUser();
    }
}
