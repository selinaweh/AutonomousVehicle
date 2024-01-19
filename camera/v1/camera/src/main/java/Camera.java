import java.lang.reflect.Method;

public class Camera {
    private static final Camera instance = new Camera();
    public Port port;
    private boolean isOn = false;

    private Camera() {
        port = new Port();
    }

    public static Camera getInstance() {
        return instance;
    }

    public boolean innerIsOn() {
        return isOn;
    }
    public String innerVersion() {
        return "CameraV1";
    }

    public boolean innerOn() {
        return isOn = true;
    }

    public boolean innerOff() {
        return isOn = false;
    }

    public class Port implements ICamera {
        private final Method[] methods = getClass().getMethods();

        public String version() {
            return innerVersion();
        }

        public boolean isOn() {
            return innerIsOn();
        }
        public boolean cameraOn() {
            return innerOn();
        }

        public boolean cameraOff() {
            return innerOff();
        }
    }
}



