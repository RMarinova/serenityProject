package UI.starter.stepdefinitions;

import UI.starter.stepsLibrary.AdminLoginSteps;
import UI.starter.stepsLibrary.MultipleUserRegistrationSteps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class MultipleUserRegistration {

    @Steps
    MultipleUserRegistrationSteps multipleUserRegistrationSteps;

    @Steps
    AdminLoginSteps adminLoginSteps;

    @When("The following details are filled in with: {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void theFollowingDetailsAreFilledInWithTitleFirstNameSirNameEmailPasswordCountryCity(String title, String firstName, String sirName, String email, String password, String country, String city) {

        multipleUserRegistrationSteps.multipleUsersRegistration(title, firstName, sirName, email, password, country, city);

    }


    @Then("The user is registered successfully.")
    public void theUserIsRegisteredSuccessfully() {

        adminLoginSteps.assertUserIsLoggedIn();

    }
}
