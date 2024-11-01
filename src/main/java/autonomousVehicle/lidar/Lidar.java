package autonomousVehicle.lidar;

import autonomousVehicle.Subscriber;
import com.google.common.eventbus.Subscribe;
import events.lidar.EventLidarOff;
import events.lidar.EventLidarOn;

public abstract class Lidar extends Subscriber {

    public Lidar() {
    }

    public abstract void turnOn();

    public abstract void turnOff();

    public abstract boolean isOn();

    @Subscribe
    public void receive(EventLidarOn lidarOn) {
        turnOn();
    }

    @Subscribe
    public void receive(EventLidarOff lidarOff) {
        turnOff();
    }
}
