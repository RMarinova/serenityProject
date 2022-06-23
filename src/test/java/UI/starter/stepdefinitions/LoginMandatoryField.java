package UI.starter.stepdefinitions;

import UI.starter.stepsLibrary.LoginMandatoryFieldSteps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class LoginMandatoryField {

    @Steps
    LoginMandatoryFieldSteps loginMandatoryFieldSteps;

    @When("The following details are filled in: {string}, {string}")
    public void theFollowingDetailsAreFilledInUserNamePassword(String userName, String password) {

        loginMandatoryFieldSteps.fillInUserDetails(userName, password);

    }

    @Then("The error message is received")
    public void theErrorMessageIsReceived() {

        loginMandatoryFieldSteps.assertLoginBtnIsPresent();

    }
}
