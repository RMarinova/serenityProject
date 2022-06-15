package UI.starter.stepdefinitions;

import WebPages.RegisterPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.core.Serenity.getDriver;

public class RegistrationMandatoryField {
    RegisterPage registerPage;
    public RegistrationMandatoryField() {

        this.registerPage = new RegisterPage(getDriver());
    }

    @When("The following details are filled in with {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void theFollowingDetailsAreFilledInWithTitleFirstNameSirNameEmailPasswordCountryCity(String title, String firstName, String sirName, String email, String password, String country, String city) {

        registerPage.fillInRegistrationDetails(title, firstName, sirName, email, password, country, city);
    }

    @Then("The registration is not successful")
    public void theRegistrationIsNotSuccessful() throws InterruptedException {

        registerPage.assertRegisterButtonIsPresent();
    }
}
