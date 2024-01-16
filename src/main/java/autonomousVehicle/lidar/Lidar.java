package autonomousVehicle.lidar;
import autonomousVehicle.Subscriber;
import com.google.common.eventbus.Subscribe;
import events.lidar.*;

public abstract class Lidar extends Subscriber{

    public Lidar() {
    }
    public abstract void turnOn();

    public abstract void turnOff();

    @Subscribe
    public void receive(EventLidarOn lidarOn) {
        turnOn();
    }
    @Subscribe
    public void receive(EventLidarOff lidarOff) {
        turnOff();
    }
}
