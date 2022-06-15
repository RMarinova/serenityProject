package REST.actions;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.DefaultUser;
import common.User;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Assert;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Map;

public class RestActions {

    private Response response;
    public static String id;
    private Faker faker;
    private BaseRestClient baseRestClient;
    private DefaultUser defaultUser;


    public RestActions() {
        faker = new Faker();
        baseRestClient = new BaseRestClient(response);
        defaultUser = new DefaultUser();

    }

    public void getResource(String path) {

        Response response = baseRestClient.getResponse(path);
        this.response = response;

    }


    public void postResourceWithLombok(String path, String text) {


        Response response = baseRestClient.postWithLombok(path, text);
        this.response = response;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String string = response.andReturn().getBody().asString();
        String body = gson.toJson(string);
        Object object = JSONValue.parse(string);

        String ID = ((JSONObject) object).get("id").toString();
        this.id = ID;
        System.out.println(body);
    }

    public void putResource(String path, String body) {

        Response response = baseRestClient.putResponse(id, path, body);
        this.response = response;
        System.out.println(response.andReturn().getBody().asString());

    }

    public void creatingNewUser(){
        String body = defaultUser.addingUserDetailsWithRest();

        Response response = baseRestClient.postWithLombok("/users", body);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String string = response.andReturn().getBody().asString();
        Object object = JSONValue.parse(string);

        String ID = ((JSONObject) object).get("id").toString();
        this.id = ID;

        String newBody = gson.toJson(string);
        System.out.println(newBody);
    }

    public void deleteUser() {
        this.response = baseRestClient.deleteResponse(id);
    }

    public void returnBody() {

        System.out.println(response.getBody().asString());
    }

    public void returnStatusCode(String statusCode, String statusMessage) {

        String statusLine = response.getStatusLine();
        String[] array = statusLine.split("\\s+");
        System.out.println(array[1] + " " + array[2]);
        Assert.assertEquals(statusCode, array[1]);
        Assert.assertEquals(statusMessage, array[2]);
    }


    public String fillInRegistrationWithLombok(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(data.get(0), User.class);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(user);

    }
}
