package REST.stepsLibrary;

import REST.models.bussinesModels.UserModel;
import REST.utils.RestActions;
import REST.utils.RestConstants;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

public class RestStepLibrary {

    private RestActions restActions;


    public RestStepLibrary() {
        restActions = new RestActions();
    }

    @Step("performing a POST request")
    public void performRestRequest(String operation, String path, String userModelString){
        if (userModelString.equalsIgnoreCase(RestConstants.USER_MODEL)) {
            UserModel userModel = new UserModel();
        Response response = restActions.performRequestTo(operation, path, userModel);
        Serenity.setSessionVariable(RestConstants.RESPONSE).to(response);
        }

    }

    @Step
    public void getUsers(){
        Response response = Serenity.sessionVariableCalled(RestConstants.RESPONSE);
        restActions.setAllUsersToBeDisplayed(response);
    }

    @Step
    public void assertStatusCodeAndMessage(String statusCode, String statusMessage){

        Response response = Serenity.sessionVariableCalled(RestConstants.RESPONSE);
        String[] arrayOfStatus = restActions.getStatusLine(response);

        Assertions.assertAll(
                () -> Assert.assertEquals("The received status code does not match what is expected!", statusCode, arrayOfStatus[1]),
                () -> Assert.assertEquals("The received status message does not match what is expected!", statusMessage, arrayOfStatus[2])
        );
    }

    @Step
    public void setUpUserDetailsWithTable(DataTable table){
        UserModel userModel = restActions.fillInRegistrationWithLombok(table);
        Serenity.setSessionVariable(RestConstants.USER_MODEL).to(userModel);
    }

    @Step
    public void aDefaultUserIsRegistered(){
        Response response = restActions.createNewDefaultUser();
        String newUserId = restActions.getNewUserId(response);
        Serenity.setSessionVariable(RestConstants.USER_ID).to(newUserId);
    }
}
