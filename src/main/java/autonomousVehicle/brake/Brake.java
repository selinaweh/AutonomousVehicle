package autonomousVehicle.brake;
import autonomousVehicle.Subscriber;
import com.google.common.eventbus.Subscribe;
import events.brake.EventBrakeSet;

public class Brake extends Subscriber{
    private int percentage;
    public Brake() {
        this.percentage = 0;
    }
    public int getPercentage() {
        return percentage;
    }

    @Subscribe
    public void receive(EventBrakeSet eventBrakeSet) {
        // TODO: implement brake event logic
    }
}
