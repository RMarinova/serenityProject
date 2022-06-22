package UI.starter.stepdefinitions;

import WebPages.HomePage;
import WebPages.RegisterPage;
import REST.models.bussinesModels.UserModel;
import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.core.Serenity.getDriver;

public class UserRegistrationWithLombok {
    private RegisterPage registerPage;
    private Faker faker;
    String email;
    private HomePage homePage;

    public UserRegistrationWithLombok() {

        registerPage = new RegisterPage(getDriver());
        faker = new Faker();
        homePage = new HomePage(getDriver());
    }


    @When("The following details are filled in using lombok")
    public void theFollowingDetailsAreFilledInUsingLombok(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        UserModel userModel = new UserModel();
        this.email = faker.internet().emailAddress();

        userModel.setFirst_name(data.get(0).get("firstName"));
        userModel.setTitle(data.get(0).get("title"));
        userModel.setSir_name(data.get(0).get("sirName"));
        userModel.setEmail(email);
        userModel.setPassword(data.get(0).get("password"));
        userModel.setCountry(data.get(0).get("country"));
        userModel.setCity(data.get(0).get("city"));

        registerPage.fillInRegistrationDetails(
                userModel.getTitle(),
                userModel.getFirst_name(),
                userModel.getSir_name(),
                userModel.getEmail(),
                userModel.getPassword(),
                userModel.getCountry(),
                userModel.getCity());
    }

    @Then("The user is registered successfully with lombok")
    public void theUserIsRegisteredSuccessfullyWithLombok() {

//        homePage.assertionLogin(email);
    }
}
