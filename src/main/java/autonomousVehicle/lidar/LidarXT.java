package autonomousVehicle.lidar;

public class LidarXT extends Lidar {
    private boolean isOn = false;

    public LidarXT() {
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public void turnOn() {
        isOn = true;
    }

    @Override
    public void turnOff() {
        isOn = false;
    }
}
