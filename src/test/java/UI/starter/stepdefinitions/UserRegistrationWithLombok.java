package steps.UI;

import POM.HomePage;
import POM.RegisterPage;
import POM.User;
import POM.UserModel;
import com.github.javafaker.Faker;
import common.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class UserRegistrationWithLombok extends BaseClass {

    private RegisterPage registerPage;
    private Faker faker;
    String email;
    private HomePage homePage;

    public UserRegistrationWithLombok() {

        registerPage = new RegisterPage(webDriver);
        faker = new Faker();
        homePage = new HomePage(webDriver);
    }


    @When("The following details are filled in using lombok")
    public void theFollowingDetailsAreFilledInUsingLombok(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        UserModel userModel = new UserModel();
        this.email = faker.internet().emailAddress();

        userModel.setFirstName(data.get(0).get("firstName"));
        userModel.setTitle(data.get(0).get("title"));
        userModel.setSirName(data.get(0).get("sirName"));
        userModel.setEmail(email);
        userModel.setPassword(data.get(0).get("password"));
        userModel.setCountry(data.get(0).get("country"));
        userModel.setCity(data.get(0).get("city"));

        registerPage.fillInRegistrationDetails(
                userModel.getTitle(),
                userModel.getFirstName(),
                userModel.getSirName(),
                userModel.getEmail(),
                userModel.getPassword(),
                userModel.getCountry(),
                userModel.getCity());
    }

    @Then("The user is registered successfully with lombok")
    public void theUserIsRegisteredSuccessfullyWithLombok() {

        homePage.assertionLogin(email);
    }
}
