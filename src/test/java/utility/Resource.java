package utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Resource {

    public static String generateStringFormResource(String path) throws IOException {

        return  new String(Files.readAllBytes(Paths.get(path)));
    }

    public static String getCreateEmployeeJson(String name,String salary,String age){
        return  "{\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"salary\": \""+salary+"\",\n" +
                "  \"age\": \""+age+"\"\n" +

                "}";
    }
}
