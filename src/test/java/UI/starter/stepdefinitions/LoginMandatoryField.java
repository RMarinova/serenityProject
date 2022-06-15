package UI.starter.stepdefinitions;

import WebPages.LoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.core.Serenity.getDriver;

public class LoginMandatoryField {

    LoginPage loginPage;
    public LoginMandatoryField() {

        this.loginPage = new LoginPage(getDriver());
    }

    @When("The following details are filled in: {string}, {string}")
    public void theFollowingDetailsAreFilledInUserNamePassword(String userName, String password) {

        loginPage.fillInUsername(userName);
        loginPage.filInPassword(password);
        
    }

    @Then("The error message is received")
    public void theErrorMessageIsReceived() throws InterruptedException {



        loginPage.assertLoginButtonIsPresent();
    }
}
