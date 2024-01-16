package main;

import autonomousVehicle.lights.brakeLight.BrakeLight;
import autonomousVehicle.centralUnit.CentralUnit;
import configuration.Configuration;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.io.File;

public class Application {
    private Object port;

    public static void main(String[] args) {

        Application application = new Application();
        application.createCameraPortInstance();

        System.out.println("Camera version: " + application.getCameraVersion());
        System.out.println("Camera on     : " + application.setCameraOn());
        System.out.println("Camera off    : " + application.setCameraOff());
    }

    public void createCameraPortInstance() {
        try {
            System.out.println("pathToJar | " + Configuration.INSTANCE.pathToJavaArchive);
            URL[] urls = {new File(Configuration.INSTANCE.pathToJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, Application.class.getClassLoader());
            Class<?> clazz = Class.forName("Camera", true, urlClassLoader);
            System.out.println("clazz     : " + clazz);

            Object instance = clazz.getMethod("getInstance").invoke(null);
            port = clazz.getDeclaredField("port").get(instance);
            System.out.println("port      : " + port.hashCode());
        } catch (Exception e) {
            System.out.println("--- exception");
            System.out.println(e.getMessage());
        }
    }

    public String getCameraVersion() {
        try {
            Method getVersion = port.getClass().getMethod("version");
            return (String) getVersion.invoke(port);
        } catch (Exception e) {
            System.out.println("Unable to get camera version.");
            return null;
        }
    }

    public boolean setCameraOn() {
        try {
            Method method = port.getClass().getMethod("cameraOn");
            return (Boolean) method.invoke(port);
        } catch (Exception e) {
            System.out.println("Unable to turn camera on.");
            return false;
        }
    }

    public boolean setCameraOff() {
        try {
            Method method = port.getClass().getMethod("cameraOff");
            return (Boolean) method.invoke(port);
        } catch (Exception e) {
            System.out.println("Unable to turn camera off.");
            return false;
        }
    }
}

