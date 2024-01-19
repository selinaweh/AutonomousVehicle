package autonomousVehicle.lidar;

public class LidarNG extends Lidar {
    private boolean isOn = false;
    public LidarNG() {
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
