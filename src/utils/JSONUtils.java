package utils;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class JSONUtils {

    // Method to write JSON data to a file
    public static void writeToJSONFile(String filePath, JSONObject jsonObject) {
        try {
            Files.write(Paths.get(filePath), jsonObject.toString(4).getBytes()); // Indented with 4 spaces for
                                                                                 // readability
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read JSON data from a file
    public static JSONObject readJSONFile(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            return new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }
}
