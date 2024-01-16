package main;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;

public enum Configuration {
    INSTANCE;

    public final String userDirectory = System.getProperty("user.dir");
    public final JSONObject jsonObject = ReadConfiguration();

    public final String cameraType = jsonObject.getString("camera_type");
    public final String engineType = jsonObject.getString("engineType");

    public JSONObject ReadConfiguration() {

        try {
            FileReader fileReader = new FileReader(userDirectory + "/configuration.json");
            JSONTokener jsonTokener = new JSONTokener(fileReader);
            JSONObject jsonObject = new JSONObject(jsonTokener);
            fileReader.close();
            return jsonObject;

        } catch (Exception e) {
            System.out.println("configuration.json File not found");
            return null;
        }
    }


}
