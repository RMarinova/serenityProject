package steps.UI;

import POM.HomePage;
import POM.LoginPage;
import common.BaseClass;
import dataProviders.ConfigReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class AdminLogin extends BaseClass {

    private LoginPage loginPage;
    private HomePage homePage;
    private String username;
    private ConfigReader configReader;

    public AdminLogin() {
        homePage = new HomePage(webDriver);
        loginPage = new LoginPage(webDriver);
        configReader = new ConfigReader();

    }

    @Given("The LogInPage is opened")
    public void theLogInPageIsOpened() {
        webDriver.get(configReader.getApplicationUrl());
    }

    @When("The username {string} is filled in")
    public void theUsernameIsFilledIn(String username) {

        this.username = username;

        loginPage.fillInUsername(username);
    }

    @And("the password {string} is filled in")
    public void thePasswordIsFilledIn(String password) {

        loginPage.filInPassword(password);
    }

    @And("the login button is clicked")
    public void theLoginButtonIsClicked() {

        loginPage.clickLoginButton();
    }

    @Then("User is logged successfully")
    public void userIsLoggedSuccessfully() {

        homePage.assertionLogin(username);
    }
}
