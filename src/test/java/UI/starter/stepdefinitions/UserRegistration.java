package UI.starter.stepdefinitions;

import UI.starter.stepsLibrary.UIStepsLibrary;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;


public class UserRegistration {

    @Steps
    UIStepsLibrary uiStepsLibrary;

    @And("The register button is clicked")
    public void theRegisterButtonIsClicked() {

        uiStepsLibrary.clickRegisterBtn();

    }

    @When("The following details are filled in with the following details but the terms of service are not clicked")
    @When("The following details are filled in with")
    public void theFollowingDetailsAreFilledInWithTitleFirstNameSirNameEmailPasswordCountryCity(DataTable table) throws InterruptedException {

        uiStepsLibrary.fillingInRegistrationDetails(table);

    }

    @And("I agree with the terms of service is clicked")
    public void iAgreeWithTheTermsOfServiceIsClicked() {

        uiStepsLibrary.clickTermsCheckbox();

    }

    @And("The register button is clicked on the register page")
    public void theRegisterButtonIsClickedAgain() {

        uiStepsLibrary.clickSignUpBtn();

    }

    @Then("The user is registered successfully")
    public void theUserIsRegisteredSuccessfully() {

        uiStepsLibrary.assertUserIsLoggedIn();

    }

}
