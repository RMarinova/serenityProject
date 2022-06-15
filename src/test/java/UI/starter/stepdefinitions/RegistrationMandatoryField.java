package steps.UI;

import POM.RegisterPage;
import common.BaseClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationMandatoryField extends BaseClass {

    RegisterPage registerPage;
    public RegistrationMandatoryField() {

        this.registerPage = new RegisterPage(webDriver);
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
