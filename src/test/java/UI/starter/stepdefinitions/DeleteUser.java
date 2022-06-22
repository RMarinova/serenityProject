package UI.starter.stepdefinitions;


import UI.starter.stepsLibrary.UIStepsLibrary;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class DeleteUser {

    @Steps
    UIStepsLibrary uiStepsLibrary;

    @And("A user is registered with the following details")
    public void aUserIsRegisteredWithTheFollowingDetails(DataTable table) {

        uiStepsLibrary.aUserIsRegistered(table);

    }

    @And("I logout from the the account")
    public void iLogoutFromTheTheAccount() {

        uiStepsLibrary.logoutFromAccount();

    }

    @When("I login with the following admin details: {string} and {string}")
    public void iLoginWithTheFollowingAdminDetailsAnd(String adminName, String password) {

        uiStepsLibrary.adminLogin(adminName, password);

    }

    @And("I click on the Users on the admin page")
    public void iClickOnTheUsersOnTheAdminPage() {

       uiStepsLibrary.clickUsersBtn();

    }

    @And("I click on the delete button next to the user")
    public void iClickOnTheDeleteButtonNextToTheUser() {

        uiStepsLibrary.deleteUser();
    }

    @Then("The user should be deleted")
    public void theUserShouldBeDeleted() {

        uiStepsLibrary.assertUserIsDeleted();
    }


}
