package UI.starter.stepdefinitions;

import UI.starter.stepsLibrary.AdminLoginSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;


public class AdminLogin {

  @Steps
  AdminLoginSteps adminLoginSteps;

    @Given("The LogInPage is opened")
    public void theLogInPageIsOpened() {

      adminLoginSteps.openHomePage();

    }

    @When("The username {string} is filled in")
    public void theUsernameIsFilledIn(String username) {

      adminLoginSteps.usernameIsFilledIn(username);

    }

    @And("the password {string} is filled in")
    public void thePasswordIsFilledIn(String password) {

      adminLoginSteps.passwordIsFilledIn(password);

    }

    @And("the login button is clicked")
    public void theLoginButtonIsClicked() {

      adminLoginSteps.clickLoginBtn();

    }

    @Then("User is logged successfully")
    public void userIsLoggedSuccessfully() {

      adminLoginSteps.assertUserIsLoggedIn();

    }
}
