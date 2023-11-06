package part12;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;

public class FileHelper {

    private static final String folderPath = "src/test/resources/json/";

    public static Object generateObjectFromResource(String jsonFile, Class resource ) {
        Object objectData = null;
        try {
            String jsonBody = new String(readAllBytes(Paths.get(folderPath + jsonFile)));
            ObjectMapper objectMapper = new ObjectMapper();
            objectData = objectMapper.readValue(jsonBody, resource);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return objectData;
    }

}