package REST.utils;

import Configuration.ConfigReader;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseRestClient {
    private ConfigReader configReader;
    Response response;

    public BaseRestClient(){
        configReader = new ConfigReader();
    }

    public Response getResponse(String path){

        return given()
                .header("Content-type", "application/json")
                .get(configReader.getAPIUrl() + path)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public Response postWithLombok(String path, Object object){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(object)
                .when()
                .post(configReader.getAPIUrl() + path)
                .then()
                .statusCode(200)
                .extract()
                .response();

    }

    public Response deleteResponse(String id){
        return given()
                .header("Content-type", "application/json")
                .delete(configReader.getAPIUrl() + "/users/" + id)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public Response putResponse(String id, String path, String body){
        return  given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .put(configReader.getAPIUrl() + path + id)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

}
