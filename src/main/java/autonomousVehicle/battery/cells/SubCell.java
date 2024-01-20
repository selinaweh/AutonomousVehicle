package autonomousVehicle.battery.cells;

import com.google.common.eventbus.EventBus;

public class SubCell extends BatteryComponent {
    private int totalEnergy;

    public SubCell(EventBus eventBus) {
        super(eventBus);
        for (int i = 0; i < 5; i++) {
            components.add(new Cell(eventBus));
        }
    }

    public int calculateTotalEnergy() {
        for (BatteryComponent component : components) {
            totalEnergy += component.calculateTotalEnergy();
        }
        return totalEnergy;
    }
}