package autonomousVehicle.camera;

import configuration.Configuration;
import exceptions.SignatureVerificationException;
import signatureVerification.SignatureVerifier;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class CameraBuilder {
    private final ArrayList<Object> cameraPorts;

    public CameraBuilder() {

        cameraPorts = new ArrayList<>(Configuration.INSTANCE.numberOfCameras);
    }

    public ArrayList<Object> buildCameras(String pathToJar) throws SignatureVerificationException {

        SignatureVerifier signatureVerifier = new SignatureVerifier();
        if (!signatureVerifier.verifySign(pathToJar)) {
            throw new SignatureVerificationException("JAR file not signed");
        }
        try {
            for (int i = 0; i < Configuration.INSTANCE.numberOfCameras; i++) {
                URL[] urls = {new File(pathToJar).toURI().toURL()};

                URLClassLoader urlClassLoader = new URLClassLoader(urls, CameraBuilder.class.getClassLoader());
                Class<?> cameraClass = Class.forName("Camera", true, urlClassLoader);
                Object cameraInstance = cameraClass.getMethod("getInstance").invoke(null);

                Object cameraPort = cameraClass.getDeclaredField("port").get(cameraInstance);
                cameraPorts.add(cameraPort);
            }
        } catch (Exception e) {
            System.out.println("Unable to get camera version. " + e.getMessage());
        }
        return cameraPorts;
    }

    public ArrayList<Object> getCameraPorts() {
        return cameraPorts;
    }
}



