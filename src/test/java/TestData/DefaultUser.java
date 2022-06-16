package TestData;

import DB.utils.UserEntity;
import REST.models.bussinesModels.UserModel;

public class DefaultUser {


    public static UserModel getDefaultUser(){
        var userModel = new UserModel();

        userModel.setTitle("Mr.");
        userModel.setFirst_name("Nikolay");
        userModel.setSir_name("Ivanov");
        userModel.setEmail("qqa@gmail.com");
        userModel.setPassword("pass123");
        userModel.setCountry("BG");
        userModel.setCity("Sofia");
        userModel.setIs_admin("0");

        return userModel;
    }

    public static UserEntity createUserWithDB(){

        var userEntity = new UserEntity();

        userEntity.setTitle("Mr.");
        userEntity.setFirst_name("Nikolay");
        userEntity.setSir_name("Ivanov");
        userEntity.setEmail("niki12s@emaill.com");
        userEntity.setPassword("pass123");
        userEntity.setCountry("BG");
        userEntity.setCity("Sofia");
        userEntity.setIs_admin("0");
        userEntity.setId("0");

        return userEntity;
    }
}
