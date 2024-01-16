import java.lang.reflect.Method;
import java.util.Arrays;
public class Camera {
    private static final Camera instance = new Camera();
    public Port port;

    private Camera() {
        port = new Port();
    }

    public static Camera getInstance() {
        return instance;
    }

    public String innerVersion() {
        return "CameraV2";
    }

    public boolean innerOn() {
        return true;
    }

    public boolean innerOff() {
        return false;
    }

    public class Port implements ICamera {
        private final Method[] methods = getClass().getMethods();

        public String version() {
            return innerVersion();
        }

        public boolean cameraOn() {
            return innerOn();
        }

        public boolean cameraOff() {
            return innerOff();
        }
    }
}


