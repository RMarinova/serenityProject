package UI.starter.stepsLibrary;

import Configuration.ConfigReader;
import UI.starter.utils.UIConstants;
import WebPages.HomePage;
import WebPages.LoginPage;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.core.Serenity.getDriver;

public class AdminLoginSteps {

    private LoginPage loginPage;
    private ConfigReader configReader;
    private HomePage homePage;

    public AdminLoginSteps() {

        this.loginPage = new LoginPage(getDriver());
        this.homePage = new HomePage(getDriver());
        this.configReader = new ConfigReader();

    }

    @Step
    public void openHomePage() {

        getDriver().get(configReader.getApplicationUrl());

    }

    @Step
    public void usernameIsFilledIn(String userName) {

        loginPage.fillInUsername(userName);
        Serenity.setSessionVariable(UIConstants.EMAIL).to(userName);

    }

    @Step
    public void passwordIsFilledIn(String password) {

        loginPage.filInPassword(password);

    }

    @Step
    public void clickLoginBtn() {

        loginPage.clickLoginButton();

    }

    @Step
    public void assertUserIsLoggedIn() {

        String username = Serenity.sessionVariableCalled(UIConstants.EMAIL);
        homePage.assertUserIsLoggedIn(username);

    }
}
