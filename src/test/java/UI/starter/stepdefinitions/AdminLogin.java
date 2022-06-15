package UI.starter.stepdefinitions;

import Configuration.ConfigReader;
import WebPages.HomePage;
import WebPages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.core.Serenity.getDriver;


public class AdminLogin {

    private LoginPage loginPage;
    private HomePage homePage;
    private String username;
    private ConfigReader configReader;

    public AdminLogin() {
        homePage = new HomePage(getDriver());
        loginPage = new LoginPage(getDriver());
        configReader = new ConfigReader();

    }

    @Given("The LogInPage is opened")
    public void theLogInPageIsOpened() {
        getDriver().get(configReader.getApplicationUrl());
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
