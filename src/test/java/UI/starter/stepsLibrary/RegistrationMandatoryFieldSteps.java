package UI.starter.stepsLibrary;

import WebPages.HomePage;
import WebPages.LoginPage;
import WebPages.RegisterPage;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.core.Serenity.getDriver;

public class RegistrationMandatoryFieldSteps {

    private RegisterPage registerPage;
    private LoginPage loginPage;
    private HomePage homePage;

    public RegistrationMandatoryFieldSteps() {

        this.registerPage = new RegisterPage(getDriver());
        this.loginPage = new LoginPage(getDriver());
        this.homePage = new HomePage(getDriver());

    }

    @Step
    public void registrationWithoutMandatoryFields(String title, String firstName, String sirName, String email, String password, String country, String city) {

        registerPage.fillInRegistrationDetails(title, firstName, sirName, email, password, country, city);

    }

    @Step
    public void assertRegisterBtnIsPresent() {

        loginPage.assertUserIsNotLoggedIn(homePage.getLoginBarElement());

    }
}
