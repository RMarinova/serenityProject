package UI.starter.stepsLibrary;

import Configuration.ConfigReader;
import UI.starter.utils.UIConstants;
import WebPages.HomePage;
import WebPages.LoginPage;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import static net.serenitybdd.core.Serenity.getDriver;

public class UIStepsLibrary {

    private LoginPage loginPage;
    private HomePage homePage;
    private ConfigReader configReader;

    public UIStepsLibrary() {
        homePage = new HomePage(getDriver());
        loginPage = new LoginPage(getDriver());
        configReader = new ConfigReader();

    }
    @Step
    public void openHomePage(){
        getDriver().get(configReader.getApplicationUrl());
    }

    @Step
    public void usernameIsFilledIn(String userName){
        loginPage.fillInUsername(userName);
        Serenity.setSessionVariable(UIConstants.USERNAME).to(userName);
    }

    @Step
    public void passwordIsFilledIn(String password){
        loginPage.filInPassword(password);
    }

    @Step
    public void clickLoginBtn(){
        loginPage.clickLoginButton();
    }

    @Step
    public void assertUserIsLoggedIn(){
        String username = Serenity.sessionVariableCalled(UIConstants.USERNAME);
        homePage.assertUserIsLoggedIn(username);
    }
}
