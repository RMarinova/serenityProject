package UI.starter.stepdefinitions;

import UI.starter.stepsLibrary.UIStepsLibrary;
import WebPages.RegisterPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import static net.serenitybdd.core.Serenity.getDriver;

public class RegistrationMandatoryField {

    @Steps
    UIStepsLibrary uiStepsLibrary;

    @When("The following details are filled in with {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void theFollowingDetailsAreFilledInWithTitleFirstNameSirNameEmailPasswordCountryCity(String title, String firstName, String sirName, String email, String password, String country, String city) {

        uiStepsLibrary.registrationWithoutMandatoryFields(title, firstName, sirName, email, password, country, city);

    }

    @Then("The registration is not successful")
    public void theRegistrationIsNotSuccessful() throws InterruptedException {

        uiStepsLibrary.assertRegisterBtnIsPresent();

    }
}
