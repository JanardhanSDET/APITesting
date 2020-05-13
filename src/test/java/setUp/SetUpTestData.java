package setUp;

import utility.Environment;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SetUpTestData {

    private String baseUrl;
    private String urnForAllEmployees;
    private String urnForCreateEmployee;

    public SetUpTestData(Environment value) {


        Properties properties = new Properties();
        FileReader fileReader = null;
        try {
            if (value.toString().equalsIgnoreCase("qa")) {
                fileReader = new FileReader("src/test/java/environment/qa.properties");
            } else if (value.toString().equalsIgnoreCase("production")) {
                fileReader = new FileReader("environment/production.properties");

            } else if (value.toString().equalsIgnoreCase("uat")) {
                fileReader = new FileReader("environment/uat.properties");

            } else if (value.toString().equalsIgnoreCase("staging")) {
                fileReader = new FileReader("environment/staging.properties");

            }
            properties.load(fileReader);

        } catch (IOException e) {
            e.printStackTrace();
        }

        baseUrl = properties.getProperty("baseUrl");
        urnForAllEmployees = properties.getProperty("urnForListOfEmployees");
        urnForCreateEmployee = properties.getProperty("urnForCreateEmployee");

    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getCreateUrn() {
        return urnForCreateEmployee;
    }

    public String getAllEmplyoeesUrn() {
        return urnForAllEmployees;
    }
}
