package observer;

import command.ElectronicKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CentralControlUnitTest {

    private CentralControlUnit centralControlUnit;

    @BeforeEach
    public void Init() throws Exception {
        ElectronicKey eKey = new ElectronicKey();
        String password = "ZooxSDC73";
        String encryptedPassword = eKey.encrypt(password);
        centralControlUnit = new CentralControlUnit(encryptedPassword);
        centralControlUnit.activate(encryptedPassword, eKey);
    }

    @Test
    void setBatteryTemperature() {
        centralControlUnit.NotifyControlUnit(1, 10, 10);
        assertEquals(10, centralControlUnit.getSensors()[1].getBatteryTemp());
        assertEquals(10, centralControlUnit.getSensors()[1].getDistance());
    }
}