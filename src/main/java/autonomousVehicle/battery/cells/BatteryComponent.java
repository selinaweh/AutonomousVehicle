package autonomousVehicle.battery.cells;

import com.google.common.eventbus.EventBus;
import events.battery.EventBatteryState;
import events.battery.EventLowBattery;

import java.util.ArrayList;
import java.util.List;

public abstract class BatteryComponent {
    protected int energy;
    protected List<BatteryComponent> components;
    protected EventBus eventBus;

    protected BatteryComponent(EventBus eventBus) {
        this.components = new ArrayList<>();
        this.eventBus = eventBus;
    }

    public void updateEnergy() {
        int oldEnergy = this.energy;
        int newEnergy = calculateTotalEnergy();
        setEnergy(newEnergy);

        if (oldEnergy != newEnergy) {
            eventBus.post(new EventBatteryState(newEnergy));
        }

        if (newEnergy < 100) {
            eventBus.post(new EventLowBattery());
        }
    }

    public void setEnergy(int energy) {
        if (energy == 0 || energy == 1) {
            this.energy = energy;
        }
    }

    public List<BatteryComponent> getComponents() {
        return this.components;
    }

    public abstract int calculateTotalEnergy();
}