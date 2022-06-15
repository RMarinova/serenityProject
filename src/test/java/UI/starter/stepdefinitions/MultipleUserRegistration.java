package steps.UI;

import POM.HomePage;
import POM.LoginPage;
import POM.RegisterPage;
import com.github.javafaker.Faker;
import common.BaseClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MultipleUserRegistration extends BaseClass {

    RegisterPage registerPage;
    LoginPage loginPage;
    Faker faker;
    HomePage homePage;

    public MultipleUserRegistration() {

        this.registerPage = new RegisterPage(webDriver);
        this.loginPage = new LoginPage(webDriver);
        this.faker =new Faker();
        this.homePage = new HomePage(webDriver);
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
