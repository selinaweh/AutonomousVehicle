package configuration;
import autonomousVehicle.electricEngine.ElectricEngine;
import autonomousVehicle.electricEngine.EngineNG;
import autonomousVehicle.electricEngine.EngineX;
import autonomousVehicle.lidar.Lidar;
import autonomousVehicle.lidar.LidarNG;
import autonomousVehicle.lidar.LidarXT;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.nio.file.FileSystems;

public enum Configuration {
    INSTANCE;

    public final String userDirectory = System.getProperty("user.dir");

    private final JSONObject jsonObject = ReadConfiguration();

    private JSONObject ReadConfiguration() {

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

    public final ElectricEngine engineType = GetEngineType();
    public final Lidar lidar = GetLidarType();

    private Lidar GetLidarType() {
        String lidarType = jsonObject.getString("lidarType");
        if (lidarType.equals("LidarXT")) {
            return new LidarXT();
        } else if (lidarType.equals("LidarNG")) {
            return new LidarNG();
        } else {
            return null;
        }
    }

    private ElectricEngine GetEngineType() {
        String engineType = jsonObject.getString("engineType");
        if (engineType.equals("EngineX")) {
            return new EngineX();
        } else if (engineType.equals("EngineNG")) {
            return new EngineNG();
        } else {
            return null;
        }
    }

    public final String fileSeparator = FileSystems.getDefault().getSeparator();
    public final String pathToJavaArchive = userDirectory + fileSeparator + "camera" + fileSeparator + getCameraType() + fileSeparator + "camera" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "camera.jar";

    public String getCameraType(){
        try {
            FileReader fileReader = new FileReader("configuration.json");
            JSONTokener jsonTokener = new JSONTokener(fileReader);
            JSONObject jsonObject = new JSONObject(jsonTokener);
            fileReader.close();
            return jsonObject.getString("cameraType");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
