package UI.starter.stepdefinitions;


import WebPages.RegisterPage;
import io.cucumber.java.en.Then;

import static net.serenitybdd.core.Serenity.getDriver;

public class MandatoryTermsOfService {
    RegisterPage registerPage;
    public MandatoryTermsOfService() {

        this.registerPage = new RegisterPage(getDriver());

    }

    @Then("An error message is displayed")
    public void anErrorMessageIsDisplayed() throws InterruptedException {

        registerPage.assertPopUpErrorMessage();
    }

}
