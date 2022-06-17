package REST.stepdefinitions;

import REST.models.bussinesModels.UserModel;
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
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

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

        if (resource.equalsIgnoreCase(RestConstants.GET_REQUEST)) {

            Response response = baseRestClient.getResponse(path);
            Serenity.setSessionVariable(RestConstants.RESPONSE).to(response);

        } else if (resource.equalsIgnoreCase(RestConstants.POST_REQUEST)) {

            UserModel userModel = Serenity.sessionVariableCalled(RestConstants.USER_MODEL);
            Response response = restActions.postResourceWithLombok(path, userModel);
            Serenity.setSessionVariable(RestConstants.RESPONSE).to(response);

            String newUserId = restActions.getNewUserId(response);
            Serenity.setSessionVariable(RestConstants.USER_ID).to(newUserId);

        } else if (resource.equalsIgnoreCase(RestConstants.DELETE_REQUEST)) {

            restActions.deleteUser();

        } else if (resource.equalsIgnoreCase(RestConstants.PUT_REQUEST)) {

            UserModel userModel = Serenity.sessionVariableCalled(RestConstants.USER_MODEL);
            Response response = restActions.putResource(path, userModel);
            Serenity.setSessionVariable(RestConstants.RESPONSE).to(response);

        }
    }

    @Then("I should receive a user with the specific id")
    @Then("I should receive all users")
    public void iShouldReceiveAllUsers() {

        Response response = Serenity.sessionVariableCalled(RestConstants.RESPONSE);
        restActions.setAllUsersToBeDisplayed(response);
    }

    @Then("I should receive status code {string} with a message {string}")
    @And("Status code {string} with a message {string}")
    public void statusCodeWithAMessage(String statusCode, String statusMessage) {

        Response response = Serenity.sessionVariableCalled(RestConstants.RESPONSE);
        String[] arrayOfStatus = restActions.getStatusLine(response);

        Assertions.assertAll(
                () -> Assert.assertEquals("The received status code does not match what is expected!", statusCode, arrayOfStatus[1]),
                () -> Assert.assertEquals("The received status message does not match what is expected!", statusMessage, arrayOfStatus[2])
        );

    }

    @And("I use the following details to update the user:")
    @When("I use the following details with lombok")
    public void iUseTheFollowingDetails(DataTable table) {

        UserModel userModel = restActions.fillInRegistrationWithLombok(table);
        Serenity.setSessionVariable(RestConstants.USER_MODEL).to(userModel);

    }

    @Given("A user is registered")
    public void aUserIsRegistered() {

        Response response = restActions.creatingNewDefaultUser();

        String newUserId = restActions.getNewUserId(response);
        Serenity.setSessionVariable(RestConstants.USER_ID).to(newUserId);

    }
}
