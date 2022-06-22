package UI.starter.stepdefinitions;

import UI.starter.stepsLibrary.UIStepsLibrary;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class LoginMandatoryField {

   @Steps
    UIStepsLibrary uiStepsLibrary;

    @When("The following details are filled in: {string}, {string}")
    public void theFollowingDetailsAreFilledInUserNamePassword(String userName, String password) {

        uiStepsLibrary.fillInUserDetails(userName, password);
        
    }

    @Then("The error message is received")
    public void theErrorMessageIsReceived() throws InterruptedException {

        uiStepsLibrary.assertLoginBtnIsPresent();

    }
}
