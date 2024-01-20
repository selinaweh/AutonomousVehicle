package observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CentralControlUnitTest {

    private CentralControlUnit centralControlUnit;
    @BeforeEach
    public void Init(){
        centralControlUnit = new CentralControlUnit();
    }

    @Test
    void setBatteryTemperature() {
        centralControlUnit.NotifyControlUnit(1, 10, 10);
        assertEquals(10, centralControlUnit.getSensors()[1].getBatteryTemp());
        assertEquals(10, centralControlUnit.getSensors()[1].getDistance());
    }
}