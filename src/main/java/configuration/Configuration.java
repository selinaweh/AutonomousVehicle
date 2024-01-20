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
    public final String fileSeparator = FileSystems.getDefault().getSeparator();
    public final String pathToJavaArchive = userDirectory + fileSeparator + "camera" + fileSeparator + getCameraType() + fileSeparator + "camera" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "signed_camera.jar";
    public final int numberOfCameras = 4;
    private final JSONObject jsonObject = readConfiguration();
    public final ElectricEngine engineType = getEngineType();
    public final Lidar lidar = getLidarType();

    private JSONObject readConfiguration() {
        try {
            FileReader fileReader = new FileReader(userDirectory + "/configuration.json");
            JSONTokener jsonTokener = new JSONTokener(fileReader);
            JSONObject jsonObject = new JSONObject(jsonTokener);
            fileReader.close();
            return jsonObject;

        } catch (Exception e) {
            System.out.println("configuration.json file not found");
            return null;
        }
    }

    private Lidar getLidarType() {
        String lidarType = jsonObject.getString("lidarType");
        if (lidarType.equals("LidarXT")) {
            return new LidarXT();
        } else if (lidarType.equals("LidarNG")) {
            return new LidarNG();
        } else {
            return null;
        }
    }

    private ElectricEngine getEngineType() {
        String engineType = jsonObject.getString("engineType");
        if (engineType.equals("EngineX")) {
            return new EngineX();
        } else if (engineType.equals("EngineNG")) {
            return new EngineNG();
        } else {
            return null;
        }
    }

    public String getCameraType() {
        try {
            FileReader fileReader = new FileReader("configuration.json");
            JSONTokener jsonTokener = new JSONTokener(fileReader);
            JSONObject jsonObject = new JSONObject(jsonTokener);
            fileReader.close();
            return jsonObject.getString("cameraType");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
