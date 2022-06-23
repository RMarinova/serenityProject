package UI.starter.stepsLibrary;

import WebPages.HomePage;
import WebPages.LoginPage;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.core.Serenity.getDriver;

public class LoginMandatoryFieldSteps {

    private LoginPage loginPage;
    private HomePage homePage;

    public LoginMandatoryFieldSteps() {

        this.loginPage = new LoginPage(getDriver());
        this.homePage = new HomePage(getDriver());

    }

    @Step
    public void fillInUserDetails(String username, String password) {

        loginPage.fillInUsername(username);
        loginPage.filInPassword(password);

    }

    @Step
    public void assertLoginBtnIsPresent() {

        loginPage.assertUserIsNotLoggedIn(homePage.getLoginBarElement());

    }

}
