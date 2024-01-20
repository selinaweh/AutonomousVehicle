package autonomousVehicle.camera;

import autonomousVehicle.Subscriber;
import com.google.common.eventbus.Subscribe;

import events.camera.EventCameraOff;
import events.camera.EventCameraOn;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class CameraMediator extends Subscriber {

    private final ArrayList<Object> cameraPorts;

    public CameraMediator(ArrayList<Object> cameraPorts) {
        this.cameraPorts = cameraPorts;
    }
    public void add(CameraBuilder cameraBuilder) {
        cameraPorts.addAll(cameraBuilder.getCameraPorts());
    }

    @Subscribe
    public void receive(EventCameraOn eventCameraOn) {
        try {
            for (Object cameraPort : cameraPorts) {
                Method cameraOnMethod = cameraPort.getClass().getDeclaredMethod("cameraOn");
                Boolean on = (Boolean) cameraOnMethod.invoke(cameraPort);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Subscribe
    public void receive(EventCameraOff eventCameraOff) {
        try {
            for (Object cameraPort : cameraPorts) {
                Method cameraOffMethod = cameraPort.getClass().getDeclaredMethod("cameraOff");
                Boolean off = (Boolean) cameraOffMethod.invoke(cameraPort);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
