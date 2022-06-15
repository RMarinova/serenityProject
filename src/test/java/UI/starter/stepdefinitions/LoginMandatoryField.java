package steps.UI;

import POM.LoginPage;
import common.BaseClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginMandatoryField extends BaseClass {

    LoginPage loginPage;

    public LoginMandatoryField() {

        this.loginPage = new LoginPage(webDriver);
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
