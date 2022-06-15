package steps.UI;

import POM.RegisterPage;
import common.BaseClass;
import io.cucumber.java.en.Then;

public class MandatoryTermsOfService extends BaseClass {
    RegisterPage registerPage;
    public MandatoryTermsOfService() {

        this.registerPage = new RegisterPage(webDriver);

    }

    @Then("An error message is displayed")
    public void anErrorMessageIsDisplayed() throws InterruptedException {

        registerPage.assertPopUpErrorMessage();
    }

}
