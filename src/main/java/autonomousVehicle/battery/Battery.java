package autonomousVehicle.battery;

import autonomousVehicle.Subscriber;
import com.google.common.eventbus.Subscribe;
import events.battery.EventBatteryState;

public class Battery extends Subscriber {
    private int energy;
    private int capacity;

    public Battery() {
        this.energy = 0;
        this.capacity = 100;
    }

    public int getEnergy() {
        return energy;
    }

    public int getCapacity() {
        return capacity;
    }
    // TODO implement battery events and their logic
    @Subscribe
    public void receive(EventBatteryState eventBatteryState) {

    }

}
