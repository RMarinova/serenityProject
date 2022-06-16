package REST.models;

import lombok.Data;

@Data
public class CreateUserResponse {
    private String title;
    private String first_name;
    private String sir_name;
    private String email;
    private String password;
    private String country;
    private String city;
    private boolean is_admin;
    private String id;
}
