package autonomousVehicle.battery;

import autonomousVehicle.battery.cells.BatteryComponent;
import autonomousVehicle.battery.cells.MainCell;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import events.battery.EventBatteryState;
import events.battery.EventCellChangeState;
import events.battery.EventLowBattery;

public class Battery extends BatteryComponent {
    private int energy;
    private int totalEnergy;

    public Battery() {
        super(new EventBus());
        for (int i = 0; i < 500; i++) {
            components.add(new MainCell(eventBus));
        }
        eventBus.register(this);
    }

    public int calculateTotalEnergy() {
        for (BatteryComponent component : components) {
            totalEnergy += component.calculateTotalEnergy();
        }
        return totalEnergy;
    }

    public void checkBattery() {
        this.updateEnergy();
    }

    public int getEnergy() {
        return calculateTotalEnergy();
    }

    public void setEnergy(int energy) {
        if (energy == 0 || energy == 1) {
            super.setEnergy(energy);
            this.energy = energy;
            eventBus.post(new EventBatteryState(energy));
        }
    }

    public int getDirectEnergy() {
        return this.energy;
    }

    public void PlugIn() {
        ChargingAdapter adapter = new ChargingAdapter();
        adapter.PlugIn2PinTo4Pin();
    }

    public void checkAndPostLowBatteryEvent() {
        if (this.getDirectEnergy() == 0) {
            eventBus.post(new EventLowBattery());
        }
    }

    @Subscribe
    public void receive(EventLowBattery eventLowBattery) {
        System.out.println("Low battery warning");
        this.PlugIn();
    }

    @Subscribe
    public void receive(EventBatteryState eventBatteryState) {
        System.out.println("Battery energy state changed: " + eventBatteryState.getEnergy());
    }

    @Subscribe
    public void receive(EventCellChangeState eventCellChangeState) {
        System.out.println("Cell state changed from " + eventCellChangeState.getOldState() + " to " + eventCellChangeState.getNewState());
    }
}