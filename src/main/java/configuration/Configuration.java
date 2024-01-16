package configuration;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.nio.file.FileSystems;

public enum Configuration {
    INSTANCE;

    public final String userDirectory = System.getProperty("user.dir");
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
