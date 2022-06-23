package UI.starter.stepdefinitions;


import UI.starter.stepsLibrary.MandatoryTermsOfServiceSteps;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;

public class MandatoryTermsOfService {

    @Steps
    MandatoryTermsOfServiceSteps mandatoryTermsOfServiceSteps;

    @Then("An error message is displayed")
    public void anErrorMessageIsDisplayed() throws InterruptedException {

        mandatoryTermsOfServiceSteps.assertErrorMessageIsDisplayed();

    }

}
