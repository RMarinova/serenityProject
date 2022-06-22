package UI.starter.stepdefinitions;

import UI.starter.stepsLibrary.UIStepsLibrary;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;


public class AdminLogin {

  @Steps
    UIStepsLibrary uiStepsLibrary;

    @Given("The LogInPage is opened")
    public void theLogInPageIsOpened() {

      uiStepsLibrary.openHomePage();

    }

    @When("The username {string} is filled in")
    public void theUsernameIsFilledIn(String username) {

        uiStepsLibrary.usernameIsFilledIn(username);
    }

    @And("the password {string} is filled in")
    public void thePasswordIsFilledIn(String password) {

        uiStepsLibrary.passwordIsFilledIn(password);
    }

    @And("the login button is clicked")
    public void theLoginButtonIsClicked() {

        uiStepsLibrary.clickLoginBtn();
    }

    @Then("User is logged successfully")
    public void userIsLoggedSuccessfully() {

        uiStepsLibrary.assertUserIsLoggedIn();
    }
}
