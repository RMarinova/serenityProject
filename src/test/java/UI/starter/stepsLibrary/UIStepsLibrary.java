package UI.starter.stepsLibrary;

import Configuration.ConfigReader;

import REST.models.bussinesModels.UserModel;
import UI.starter.models.UiUserModel;
import UI.starter.utils.UIConstants;
import WebPages.HomePage;
import WebPages.LoginPage;
import WebPages.RegisterPage;
import WebPages.UsersPage;
import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.core.Serenity.getDriver;

public class UIStepsLibrary {

    private LoginPage loginPage;
    private HomePage homePage;
    private ConfigReader configReader;
    private RegisterPage registerPage;
    private UsersPage usersPage;
    private Faker faker;

    public UIStepsLibrary() {
        this.homePage = new HomePage(getDriver());
        this.loginPage = new LoginPage(getDriver());
        this.configReader = new ConfigReader();
        this.registerPage = new RegisterPage(getDriver());
        this.usersPage = new UsersPage(getDriver());
        this.faker = new Faker();
    }

    @Step
    public void openHomePage() {
        getDriver().get(configReader.getApplicationUrl());
    }

    @Step
    public void usernameIsFilledIn(String userName) {
        loginPage.fillInUsername(userName);
        Serenity.setSessionVariable(UIConstants.USERNAME).to(userName);
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

    @Step
    public void aUserIsRegistered(DataTable table) {

        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        ModelMapper modelMapper = new ModelMapper();
        UiUserModel user = modelMapper.map(data.get(0), UiUserModel.class);

        registerPage.fillInRegistrationDetails(user.getTitle(), user.getFirstName(), user.getSirName(), user.getEmail(), user.getPassword(), user.getCountry(), user.getCity());
        registerPage.clickTermsCheckbox();
        registerPage.clickRegisterBtn();

        Serenity.setSessionVariable(UIConstants.EMAIL).to(user.getEmail());

    }

    @Step
    public void logoutFromAccount() {
        homePage.logoutFromAccount();
    }

    @Step
    public void adminLogin(String username, String password) {
        loginPage.fillInUsername(username);
        loginPage.filInPassword(password);
        loginPage.clickLoginButton();
    }

    @Step
    public void clickUsersBtn() {
        homePage.ClickUsersButton();
    }

    @Step
    public void deleteUser() {
        String email = Serenity.sessionVariableCalled(UIConstants.EMAIL);
        usersPage.deleteUser(email);
    }

    @Step
    public void assertUserIsDeleted() {
        String email = Serenity.sessionVariableCalled(UIConstants.EMAIL);
        usersPage.assertUserIsDeleted(email);
    }

    @Step
    public void fillInUserDetails(String username, String password) {
        loginPage.fillInUsername(username);
        loginPage.filInPassword(password);
    }

    @Step
    public void assertLoginBtnIsPresent() throws InterruptedException {
        loginPage.assertLoginButtonIsPresent();
    }

    @Step
    public void assertErrorMessageIsDisplayed() throws InterruptedException {
        registerPage.assertPopUpErrorMessage();
    }

    @Step
    public void multipleUsersRegistration(String title, String firstName, String sirName, String email, String password, String country, String city) {

        email = faker.internet().emailAddress();
        registerPage.fillInRegistrationDetails(title, firstName, sirName, email, password, country, city);
        Serenity.setSessionVariable(UIConstants.EMAIL).to(email);
    }

    @Step
    public void registrationWithoutMandatoryFields(String title, String firstName, String sirName, String email, String password, String country, String city) {
        registerPage.fillInRegistrationDetails(title, firstName, sirName, email, password, country, city);
    }

    @Step
    public void assertRegisterBtnIsPresent() throws InterruptedException {
        registerPage.assertRegisterButtonIsPresent();

    }

    @Step
    public void fillingInRegistrationDetails(DataTable table) {

        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        ModelMapper modelMapper = new ModelMapper();
        UiUserModel user = modelMapper.map(data.get(0), UiUserModel.class);
        user.setEmail(faker.internet().emailAddress());
        registerPage.fillInRegistrationDetails(user.getTitle(), user.getFirstName(), user.getSirName(), user.getEmail(), user.getPassword(), user.getCountry(), user.getCity());

        Serenity.setSessionVariable(UIConstants.EMAIL).to(user.getEmail());

    }

    @Step
    public void clickRegisterBtn() {
        loginPage.clickRegisterButton();
    }

    @Step
    public void clickTermsCheckbox() {
        registerPage.clickTermsCheckbox();
    }

    @Step
    public void clickSignUpBtn() {
        registerPage.clickRegisterBtn();
    }
}
