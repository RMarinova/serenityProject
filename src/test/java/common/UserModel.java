package common;

import lombok.Data;

@Data
public class UserModel {

    private String title;
    private String firstName;
    private String sirName;
    private String email;
    private String password;
    private String country;
    private String city;
    private String is_admin;
    private String id;
}
