package steps.UI;

import POM.HomePage;
import POM.LoginPage;
import POM.RegisterPage;
import POM.UsersPage;
import common.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class DeleteUser extends BaseClass {

    RegisterPage registerPage;
    HomePage homePage;
    LoginPage loginPage;
    UsersPage usersPage;

    String userName;

    public DeleteUser() {

        this.registerPage = new RegisterPage(webDriver);
        this.homePage = new HomePage(webDriver);
        this.loginPage = new LoginPage(webDriver);
        this.usersPage = new UsersPage(webDriver);
    }

    @And("A user is registered with the following details")
    public void aUserIsRegisteredWithTheFollowingDetails(DataTable table) {

        List<Map<String, String>> data = table.asMaps(String.class, String.class);


        String title = data.get(0).get("title");
        String firstName = data.get(0).get("firstName");
        String sirName = data.get(0).get("sirName");
        String email = data.get(0).get("email");
        String password = data.get(0).get("password");
        String country = data.get(0).get("country");
        String city = data.get(0).get("city");

        this.userName = email;

        registerPage.fillInRegistrationDetails(title, firstName, sirName, email, password, country, city);
        registerPage.termsCheckbox().click();
        registerPage.registerButton().click();
    }

    @And("I logout from the the account")
    public void iLogoutFromTheTheAccount() throws InterruptedException {
        Thread.sleep(2000);
        homePage.logoutFromAccount();
    }

    @When("I login with the following admin details: {string} and {string}")
    public void iLoginWithTheFollowingAdminDetailsAnd(String adminName, String password) {

        loginPage.fillInUsername(adminName);
        loginPage.filInPassword(password);
        loginPage.clickLoginButton();
    }

    @And("I click on the Users on the admin page")
    public void iClickOnTheUsersOnTheAdminPage() {

        homePage.ClickUsersButton();
    }

    @And("I click on the delete button next to the user")
    public void iClickOnTheDeleteButtonNextToTheUser() {

        usersPage.deleteUser(userName);
    }

    @Then("The user should be deleted")
    public void theUserShouldBeDeleted() {

        usersPage.assertUserIsDeleted(userName);
    }


}
