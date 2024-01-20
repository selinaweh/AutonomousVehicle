package component;
import autonomousVehicle.AutonomousVehicle;
import autonomousVehicle.camera.CameraBuilder;
import configuration.JSONConfiguration;
import exceptions.SignatureVerificationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.FileSystems;

import static org.junit.jupiter.api.Assertions.*;
public class TestComponent {
    private AutonomousVehicle autonomousVehicle;
    public final String userDirectory = System.getProperty("user.dir");
    public final String fileSeparator = FileSystems.getDefault().getSeparator();
    public final String pathToUnsignedJar = userDirectory + fileSeparator + "camera" + fileSeparator + "v1" + fileSeparator + "camera" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "camera.jar";
    public final String pathToSignedJar = userDirectory + fileSeparator + "camera" + fileSeparator + "v1" + fileSeparator + "camera" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "signed_camera.jar";
    @BeforeEach
    public void setUp() {
        autonomousVehicle = new AutonomousVehicle.Builder()
                .Chassis()
                .ElectricEngine()
                .Battery()
                .LEDHeadlight()
                .BrakeLight()
                .Indicator()
                .Door()
                .Seat()
                .Wheel()
                .Brake()
                .GPS()
                .Camera()
                .Lidar()
                .build();
    }
    @AfterEach
    public void tearDown() {
        JSONConfiguration jsonConfiguration = new JSONConfiguration();
        jsonConfiguration.build("v1", "EngineX", "LidarXT");
    }

    @Test
    @Order(1)
    public void testCameraPortsEqualsNumberOfCameras() {
        assertEquals(4, autonomousVehicle.getCameraPorts().size());
    }

    @Test
    @Order(2)
    public void testCameraVersion() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (Object cameraPort : autonomousVehicle.getCameraPorts()) {
            Method versionMethod = cameraPort.getClass().getMethod("version");
            String version = (String) versionMethod.invoke(cameraPort);
            assertEquals("CameraV1", version);
        }
    }

    @Test
    @Order(3)
    public void testCamerasBuildWithSignedJar() throws SignatureVerificationException {
        CameraBuilder cameraBuilder = new CameraBuilder();
        cameraBuilder.buildCameras(pathToSignedJar);
        assertEquals(4, cameraBuilder.getCameraPorts().size());
    }
    @Test
    @Order(4)
    public void testNotSignedNotBuild(){
        CameraBuilder cameraBuilder = new CameraBuilder();
        assertThrows(SignatureVerificationException.class, () -> cameraBuilder.buildCameras(pathToUnsignedJar));
        assertEquals(0, cameraBuilder.getCameraPorts().size());
    }
}
