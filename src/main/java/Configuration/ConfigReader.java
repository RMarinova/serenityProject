package Configuration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;
    private final String propertyFilePath = "serenity.properties";


    public ConfigReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        if (url != null) return url;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }


    public String getAPIUrl() {
        String url = properties.getProperty("api_url");
        if (url != null) return url;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public String getUsersPath(){
        String usersPath = properties.getProperty("api_users_path");
        return usersPath;
    }

    public String getDBUrl() {
        String url = properties.getProperty("database.local.url");
        if (url != null) return url;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public String getDBUsername(){
       String username =  properties.getProperty("database.local.username");
       return username;
    }

    public String getDBPassword(){
        String password =  properties.getProperty("database.local.password");
        return password;
    }

}

