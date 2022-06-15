package REST.common;

import POM.User;
import POM.UserModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DefaultUser {

    private User user;
    private UserModel userModel;

    public DefaultUser(){
        user = new User();
        userModel = new UserModel();
    }

    public String addingUserDetailsWithRest(){
        user.setTitle("Mr.");
        user.setFirst_name("Nikolay");
        user.setSir_name("Ivanov");
        user.setEmail("niki@email.com");
        user.setPassword("pass123");
        user.setCountry("BG");
        user.setCity("Sofia");
        user.setIs_admin("0");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(user);
    }

    public UserModel createUserWithDB(){
        userModel.setTitle("Mr.");
        userModel.setFirstName("Nikolay");
        userModel.setSirName("Ivanov");
        userModel.setEmail("niki@email.com");
        userModel.setPassword("pass123");
        userModel.setCountry("BG");
        userModel.setCity("Sofia");
        userModel.setIs_admin("0");
        userModel.setId("0");
        return userModel;
    }
}
