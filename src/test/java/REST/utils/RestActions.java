package REST.utils;

import REST.models.CreateUserResponse;
import REST.models.bussinesModels.UserModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import TestData.DefaultUser;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Assert;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Map;

public class RestActions {

    private BaseRestClient baseRestClient;

    public RestActions() {

        baseRestClient = new BaseRestClient();

    }

    public void postResourceWithLombok(String path, String text) {


        Response response = baseRestClient.postWithLombok(path, text);
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        var createUserResponse = response.andReturn().getBody().as(CreateUserResponse.class);
        var id = createUserResponse.getId();
        Serenity.environmentVariables().setProperty(RestConstants.USER_ID, id);
    }

    public void putResource(String path, String body) {
        var id = Serenity.environmentVariables().getProperty(RestConstants.USER_ID);
        Response response = baseRestClient.putResponse(id, path, body);

        System.out.println(response.andReturn().getBody().asString());

    }

    public void creatingNewUser(){
        var createUserRequest = DefaultUser.getDefaultUser();
        Response response = baseRestClient.postWithLombok("/users", createUserRequest);
        var createUserResponse = response.andReturn().getBody().as(CreateUserResponse.class);
        var id = createUserResponse.getId();
        Serenity.environmentVariables().setProperty(RestConstants.USER_ID, id);



        String string = response.andReturn().getBody().asString();
        Object object = JSONValue.parse(string);

        String id = ((JSONObject) object).get("id").toString();
        Serenity.environmentVariables().setProperty("id", id);

    }

    public void deleteUser() {
         baseRestClient.deleteResponse(Serenity.environmentVariables().getProperty("id"));
    }

    public void returnStatusCode(String statusCode, String statusMessage) {
        Response response = Serenity.sessionVariableCalled("response");

        String[] array = response.statusLine().split("\\s+");
        System.out.println(array[1] + " " + array[2]);

        Assert.assertEquals(statusCode, array[1]);
        Assert.assertEquals(statusMessage, array[2]);
    }


    public String fillInRegistrationWithLombok(DataTable table) {

        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        UserModel user = null;

        for (int i = 0; i < data.size(); i++) {

            ModelMapper modelMapper = new ModelMapper();
            user = modelMapper.map(data.get(i), UserModel.class);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(user);

    }
}
