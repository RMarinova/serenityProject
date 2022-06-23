package UI.starter.stepsLibrary;

import UI.starter.models.UiUserModel;
import UI.starter.utils.UIConstants;
import WebPages.HomePage;
import WebPages.LoginPage;
import WebPages.RegisterPage;
import WebPages.UsersPage;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.core.Serenity.getDriver;

public class DeleteUserSteps {

    private RegisterPage registerPage;
    private LoginPage loginPage;
    private HomePage homePage;
    private UsersPage usersPage;

    public DeleteUserSteps() {

        this.registerPage = new RegisterPage(getDriver());
        this.homePage = new HomePage(getDriver());
        this.loginPage = new LoginPage(getDriver());
        this.usersPage = new UsersPage(getDriver());

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
}
