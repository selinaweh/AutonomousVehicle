package configuration;

import org.json.JSONObject;

import java.io.FileWriter;

public class JSONConfiguration {

    public void build(String cameraType, String engineType, String lidarType) {
        try {
            JSONObject jsonObject = new JSONObject();

            switch (cameraType) {
                case "v1" -> jsonObject.put("cameraType", "v1");
                case "v2" -> jsonObject.put("cameraType", "v2");
                default -> jsonObject.put("cameraType", "invalid");
            }
            switch (engineType) {
                case "EngineX" -> jsonObject.put("engineType", "EngineX");
                case "EngineNG" -> jsonObject.put("engineType", "EngineNG");
                default -> jsonObject.put("engineType", "invalid");
            }
            switch (lidarType) {
                case "LidarXT" -> jsonObject.put("lidarType", "LidarXT");
                case "LidarNG" -> jsonObject.put("lidarType", "LidarNG");
                default -> jsonObject.put("lidarType", "invalid");
            }

            FileWriter fileWriter = new FileWriter("configuration.json");
            fileWriter.write(jsonObject.toString());
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
