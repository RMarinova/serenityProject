package REST.models.bussinesModels;

import lombok.Data;

@Data
public class UserModel {

    private String title;
    private String first_name;
    private String sir_name;
    private String email;
    private String password;
    private String country;
    private String city;
    private String is_admin;

}
