package configuration;

import org.json.JSONObject;

import java.io.FileWriter;

public class JSONConfiguration {
    public static void main(String... args) {
        JSONConfiguration jsonConfiguration = new JSONConfiguration();
        jsonConfiguration.build("v1");
    }

    public void build(String cameraType) {
        try {
            JSONObject jsonObject = new JSONObject();

            switch (cameraType) {
                case "v1" -> jsonObject.put("camera_type", "v1");
                case "v2" -> jsonObject.put("camera_type", "v2");
                default -> jsonObject.put("camera_type", "invalid");
            }

            FileWriter fileWriter = new FileWriter("configuration.json");
            fileWriter.write(jsonObject.toString());
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
