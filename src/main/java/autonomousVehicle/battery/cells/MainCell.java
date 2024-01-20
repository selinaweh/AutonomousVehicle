package autonomousVehicle.battery.cells;

import com.google.common.eventbus.EventBus;

public class MainCell extends BatteryComponent {
    private int totalEnergy;

    public MainCell(EventBus eventBus) {
        super(eventBus);
        for (int i = 0; i < 100; i++) {
            components.add(new SubCell(eventBus));
        }
    }

    public int calculateTotalEnergy() {
        for (BatteryComponent component : components) {
            totalEnergy += component.calculateTotalEnergy();
        }
        return totalEnergy;
    }
}