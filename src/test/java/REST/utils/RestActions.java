package REST.utils;

import Configuration.ConfigReader;
import REST.models.CreateUserResponse;
import REST.models.bussinesModels.UserModel;
import TestData.DefaultUser;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Map;

public class RestActions {

    private BaseRestClient baseRestClient;
    private ConfigReader configReader;

    public RestActions() {

        baseRestClient = new BaseRestClient();
        configReader = new ConfigReader();

    }

    private Response putResource(String path, Object body) {

        String id = Serenity.sessionVariableCalled(RestConstants.USER_ID);
        Response response = baseRestClient.putResponse(id, path, body);
        response.getBody().prettyPrint();
        return response;

    }

    public void setAllUsersToBeDisplayed(Response response) {
        response.getBody().prettyPrint();
    }

    public String[] getStatusLine(Response response) {

        String[] arrayOfStatusLine = response.statusLine().split("\\s+");
        System.out.println(arrayOfStatusLine[1] + " " + arrayOfStatusLine[2]);

        return arrayOfStatusLine;
    }

    public UserModel fillInRegistrationWithLombok(DataTable table) {

        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        UserModel user = null;
        ModelMapper modelMapper = new ModelMapper();

        for (int i = 0; i < data.size(); i++) {

            user = modelMapper.map(data.get(i), UserModel.class);
        }

        return user;
    }

    private Response postResource(String path, Object body) {


        Response response = baseRestClient.postResponse(path, body);
        response.getBody().prettyPrint();
        return response;
    }

    public Response createNewDefaultUser() {

        var createUserRequest = DefaultUser.getDefaultUser();
        Response response = baseRestClient.postResponse(configReader.getUsersPath(), createUserRequest);
        response.getBody().prettyPrint();

        return response;
    }

    public String getNewUserId(Response response) {

        var createUserResponse = response.andReturn().getBody().as(CreateUserResponse.class);
        return createUserResponse.getId();
    }

    public Response deleteUser() {
        Response response = baseRestClient.deleteResponse(Serenity.sessionVariableCalled(RestConstants.USER_ID));
        return response;
    }

    public Response performRequestTo(String operation, String path, Object userModel) {
        Response response = null;

        if (operation.equalsIgnoreCase(RestConstants.GET_REQUEST)) {

            response = baseRestClient.getResponse(path);
            return response;

        } else if (operation.equalsIgnoreCase(RestConstants.POST_REQUEST)) {

            userModel = Serenity.sessionVariableCalled(RestConstants.USER_MODEL);
            response = postResource(path, userModel);

            String newUserId = getNewUserId(response);
            Serenity.setSessionVariable(RestConstants.USER_ID).to(newUserId);
            return response;

        } else if (operation.equalsIgnoreCase(RestConstants.DELETE_REQUEST)) {

            response = deleteUser();
            return response;

        } else if (operation.equalsIgnoreCase(RestConstants.PUT_REQUEST)) {

            userModel = Serenity.sessionVariableCalled(RestConstants.USER_MODEL);
            response = putResource(path, userModel);
            return response;
        }
        return response;
    }


}
