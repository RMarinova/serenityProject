package steps.UI;

import POM.HomePage;
import POM.LoginPage;
import POM.RegisterPage;
import com.github.javafaker.Faker;
import common.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Map;


public class UserRegistration extends BaseClass {

    private LoginPage loginPage;
    private RegisterPage registerPage;
    private String username;
    private Faker faker;
    HomePage homePage;

    public UserRegistration() {
        loginPage = new LoginPage(webDriver);
        registerPage = new RegisterPage(webDriver);
        faker = new Faker();
        homePage = new HomePage(webDriver);

    }

    @And("The register button is clicked")
    public void theRegisterButtonIsClicked() {

        loginPage.clickRegisterButton();
    }

    @When("The following details are filled in with the following details but the terms of service are not clicked")
    @When("The following details are filled in with")
    public void theFollowingDetailsAreFilledInWithTitleFirstNameSirNameEmailPasswordCountryCity(DataTable table) throws InterruptedException {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

            String email = faker.internet().emailAddress();


        String title = data.get(0).get("title");
        String firstName = data.get(0).get("firstName");
        String sirName = data.get(0).get("sirName");
        String password = data.get(0).get("password");
        String country = data.get(0).get("country");
        String city = data.get(0).get("city");

        this.username = email;
        registerPage.fillInRegistrationDetails(title, firstName, sirName, email, password, country, city);

    }

    @And("I agree with the terms of service is clicked")
    public void iAgreeWithTheTermsOfServiceIsClicked() {

        registerPage.termsCheckbox().click();
    }

    @And("The register button is clicked on the register page")
    public void theRegisterButtonIsClickedAgain() {

        registerPage.registerButton().click();
    }

    @Then("The user is registered successfully")
    public void theUserIsRegisteredSuccessfully() {

        homePage.assertionLogin(username);
    }

}
