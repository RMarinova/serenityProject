package UI.starter.stepdefinitions;

import WebPages.HomePage;
import WebPages.LoginPage;
import WebPages.RegisterPage;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.core.Serenity.getDriver;

public class MultipleUserRegistration {
    RegisterPage registerPage;
    LoginPage loginPage;
    Faker faker;
    HomePage homePage;

    public MultipleUserRegistration() {

        this.registerPage = new RegisterPage(getDriver());
        this.loginPage = new LoginPage(getDriver());
        this.faker =new Faker();
        this.homePage = new HomePage(getDriver());
    }

    String username;

    @When("The following details are filled in with: {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void theFollowingDetailsAreFilledInWithTitleFirstNameSirNameEmailPasswordCountryCity(String title, String firstName, String sirName, String email, String password, String country, String city) {

        email = faker.internet().emailAddress();
        this.username = email;
        registerPage.fillInRegistrationDetails(title, firstName, sirName, email, password, country, city);
    }


    @Then("The user is registered successfully.")
    public void theUserIsRegisteredSuccessfully() {

        homePage.assertionLogin(username);
    }
}
