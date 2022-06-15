package common;

public class DBQueries {

    public final String selectAll = "SELECT * FROM db.users;";

    public String addNewUser(String id,String admin, String firstName, String sirName, String title, String country, String city, String email, String password){
        String query = String.format("INSERT INTO db.users (id, is_admin, first_name, sir_name, title, country, city, email, password) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s');",
                id,
                admin,
                firstName,
                sirName,
                title,
                country,
                city,
                email,
                password);

        System.out.println(query);
        return query;
    }

    public String selectWithEmail(String email){
        return "SELECT * FROM db.users WHERE email= '" + email + "';";
    }

    public String deleteUser(String email){
        return "DELETE FROM db.users Where email= '" + email + "';";
    }

    public String updateFirstAndLastName(String firstName, String lastName, String email){
        return "UPDATE db.users SET first_name ='" + firstName + "', sir_name ='" + lastName + "' WHERE email='" + email + "';";
    }
}
