package UI.starter.stepdefinitions;

import UI.starter.stepsLibrary.RegistrationMandatoryFieldSteps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class RegistrationMandatoryField {

    @Steps
    RegistrationMandatoryFieldSteps registrationMandatoryFieldSteps;

    @When("The following details are filled in with {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void theFollowingDetailsAreFilledInWithTitleFirstNameSirNameEmailPasswordCountryCity(String title, String firstName, String sirName, String email, String password, String country, String city) {

        registrationMandatoryFieldSteps.registrationWithoutMandatoryFields(title, firstName, sirName, email, password, country, city);

    }

    @Then("The registration is not successful")
    public void theRegistrationIsNotSuccessful() {

        registrationMandatoryFieldSteps.assertRegisterBtnIsPresent();

    }
}
