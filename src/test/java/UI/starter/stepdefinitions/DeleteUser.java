package UI.starter.stepdefinitions;


import UI.starter.stepsLibrary.DeleteUserSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class DeleteUser {

    @Steps
    DeleteUserSteps deleteUserSteps;

    @And("A user is registered with the following details")
    public void aUserIsRegisteredWithTheFollowingDetails(DataTable table) {

        deleteUserSteps.aUserIsRegistered(table);

    }

    @And("I logout from the the account")
    public void iLogoutFromTheTheAccount() {

        deleteUserSteps.logoutFromAccount();

    }

    @When("I login with the following admin details: {string} and {string}")
    public void iLoginWithTheFollowingAdminDetailsAnd(String adminName, String password) {

        deleteUserSteps.adminLogin(adminName, password);

    }

    @And("I click on the Users on the admin page")
    public void iClickOnTheUsersOnTheAdminPage() {

        deleteUserSteps.clickUsersBtn();

    }

    @And("I click on the delete button next to the user")
    public void iClickOnTheDeleteButtonNextToTheUser() {

        deleteUserSteps.deleteUser();

    }

    @Then("The user should be deleted")
    public void theUserShouldBeDeleted() {

        deleteUserSteps.assertUserIsDeleted();

    }


}
