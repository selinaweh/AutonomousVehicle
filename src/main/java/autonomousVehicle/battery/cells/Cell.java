package autonomousVehicle.battery.cells;

import com.google.common.eventbus.EventBus;

public class Cell extends BatteryComponent {
    public Cell(EventBus eventBus) {
        super(eventBus);
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        if (energy == 0 || energy == 1) {
            super.setEnergy(energy);
        } else {
            throw new IllegalArgumentException("Energy must be 0 (discharged) or 1 (charged)");
        }
    }

    public int calculateTotalEnergy() {
        return energy;
    }
}