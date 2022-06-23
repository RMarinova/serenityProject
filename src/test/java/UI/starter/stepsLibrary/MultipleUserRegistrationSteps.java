package UI.starter.stepsLibrary;

import UI.starter.utils.UIConstants;
import WebPages.RegisterPage;
import com.github.javafaker.Faker;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.core.Serenity.getDriver;

public class MultipleUserRegistrationSteps {

    private Faker faker;
    private RegisterPage registerPage;

    public MultipleUserRegistrationSteps() {

        this.faker = new Faker();
        this.registerPage = new RegisterPage(getDriver());

    }

    @Step
    public void multipleUsersRegistration(String title, String firstName, String sirName, String email, String password, String country, String city) {

        email = faker.internet().emailAddress();
        registerPage.fillInRegistrationDetails(title, firstName, sirName, email, password, country, city);
        Serenity.setSessionVariable(UIConstants.EMAIL).to(email);

    }
}
