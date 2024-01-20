package events.battery;

public class EventBatteryState {
    private final int energy;

    public EventBatteryState(int energy) {
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }
}
