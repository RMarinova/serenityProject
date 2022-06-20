package REST.stepdefinitions;

import REST.models.bussinesModels.UserModel;
import REST.utils.RestActions;
import REST.utils.RestConstants;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

public class CRUD {


    private RestActions restActions;


    public CRUD() {
        restActions = new RestActions();
    }


    @Steps
    UserModel userModel;

    @When("I perform a {string} request to {string}")
    public void iPerformARequestTo(String operation, String path) {

        Response response = restActions.performRequestTo(operation, path, userModel);
        Serenity.setSessionVariable(RestConstants.RESPONSE).to(response);

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

        Response response = restActions.createNewDefaultUser();
        String newUserId = restActions.getNewUserId(response);
        Serenity.setSessionVariable(RestConstants.USER_ID).to(newUserId);

    }
}
