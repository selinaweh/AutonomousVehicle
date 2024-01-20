package autonomousVehicle;

import autonomousVehicle.battery.Battery;
import autonomousVehicle.battery.cells.BatteryComponent;
import autonomousVehicle.battery.cells.Cell;
import com.google.common.eventbus.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CompositeTest {
    private AutonomousVehicle autonomousVehicle;

    @BeforeEach
    public void setUp() {
        autonomousVehicle = new AutonomousVehicle.Builder()
                .Chassis()
                .ElectricEngine()
                .Battery()
                .LEDHeadlight()
                .BrakeLight()
                .Indicator()
                .Door()
                .Seat()
                .Wheel()
                .Brake()
                .GPS()
                .Camera()
                .Lidar()
                .build();
    }

    @Test
    public void testBatteryComposition() {
        Battery battery = autonomousVehicle.getBattery();
        assertEquals(500, battery.getComponents().size());
        for (BatteryComponent mainCell : battery.getComponents()) {
            assertEquals(100, mainCell.getComponents().size());
            for (BatteryComponent subCell : mainCell.getComponents()) {
                assertEquals(5, subCell.getComponents().size());
            }
        }
    }

    @Test
    public void testSetEnergy() {
        Cell cell = new Cell(new EventBus());
        cell.setEnergy(1);
        assertEquals(1, cell.getEnergy());
        cell.setEnergy(0);
        assertEquals(0, cell.getEnergy());

        try {
            cell.setEnergy(2);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Energy must be 0 (discharged) or 1 (charged)", e.getMessage());
        }
    }

}
