package UI.starter.stepsLibrary;

import UI.starter.models.UiUserModel;
import UI.starter.utils.UIConstants;
import WebPages.LoginPage;
import WebPages.RegisterPage;
import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.core.Serenity.getDriver;

public class UserRegistrationSteps {

    private RegisterPage registerPage;
    private LoginPage loginPage;
    private Faker faker;

    public UserRegistrationSteps() {

        this.loginPage = new LoginPage(getDriver());
        this.registerPage = new RegisterPage(getDriver());
        this.faker = new Faker();

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
