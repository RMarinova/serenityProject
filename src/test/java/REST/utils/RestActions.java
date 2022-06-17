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

    public Response putResource(String path, Object body) {

        String id = Serenity.sessionVariableCalled(RestConstants.USER_ID);
        Response response = baseRestClient.putResponse(id, path, body);
        response.getBody().prettyPrint();
        return response;

    }

    public void setAllUsersToBeDisplayed(Response response){
        response.getBody().prettyPrint();
    }

    public String[] getStatusLine(Response response){

        String[] arrayOfStatusLine = response.statusLine().split("\\s+");
        System.out.println(arrayOfStatusLine[1] + " " + arrayOfStatusLine[2]);

        return arrayOfStatusLine;
    }

    public UserModel fillInRegistrationWithLombok(DataTable table) {

        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        UserModel user = null;

        for (int i = 0; i < data.size(); i++) {

            ModelMapper modelMapper = new ModelMapper();
            user = modelMapper.map(data.get(i), UserModel.class);
        }

        return user;
    }

    public Response postResourceWithLombok(String path, Object body) {


        Response response = baseRestClient.postWithLombok(path, body);
        response.getBody().prettyPrint();
        return response;
    }

    public Response creatingNewDefaultUser(){

        var createUserRequest = DefaultUser.getDefaultUser();
        Response response = baseRestClient.postWithLombok(configReader.getUsersPath(), createUserRequest);
        response.getBody().prettyPrint();

        return response;
    }

    public String getNewUserId(Response response){

        var createUserResponse = response.andReturn().getBody().as(CreateUserResponse.class);
        return createUserResponse.getId();
    }

    public void deleteUser() {
        baseRestClient.deleteResponse(Serenity.sessionVariableCalled(RestConstants.USER_ID));

    }
}
