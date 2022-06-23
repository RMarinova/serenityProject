package UI.starter.stepsLibrary;

import WebPages.RegisterPage;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.core.Serenity.getDriver;

public class MandatoryTermsOfServiceSteps {

    private RegisterPage registerPage;

    public MandatoryTermsOfServiceSteps() {

        this.registerPage = new RegisterPage(getDriver());

    }

    @Step
    public void assertErrorMessageIsDisplayed() {

        registerPage.assertPopUpErrorMessage();

    }

}
