package UI.starter.stepdefinitions;


import UI.starter.stepsLibrary.UIStepsLibrary;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

public class MandatoryTermsOfService {

    @Steps
    UIStepsLibrary uiStepsLibrary;

    @Then("An error message is displayed")
    public void anErrorMessageIsDisplayed() throws InterruptedException {

        uiStepsLibrary.assertErrorMessageIsDisplayed();

    }

}
