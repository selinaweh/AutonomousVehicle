package command;

import autonomousVehicle.body.Door;
import observer.CentralControlUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandTests {

    @Test
    public void testDoorOpenClose() {
        Door door = new Door(false);
        OpenDoorCommand openDoorCommand = new OpenDoorCommand(door);
        CloseDoorCommand closeDoorCommand = new CloseDoorCommand(door);

        openDoorCommand.execute();
        assertTrue(door.isOpen());

        closeDoorCommand.execute();
        assertFalse(door.isOpen());
    }

    @Test
    public void testCentralControlUnitActivation() throws Exception {
        String password = "ZooxSDC73";
        ElectronicKey eKey = new ElectronicKey();
        String encryptedPassword = eKey.encrypt(password);
        CentralControlUnit ccu = new CentralControlUnit(encryptedPassword);

        ccu.activate(encryptedPassword, eKey);
        assertTrue(ccu.isActive());

        ccu.activate(encryptedPassword, eKey);
        assertFalse(ccu.isActive());
    }

    @Test
    public void testPasswordEncryptionDecryption() throws Exception {
        String password = "ZooxSDC73";
        ElectronicKey eKey = new ElectronicKey();

        String encryptedPassword = eKey.encrypt(password);
        String decryptedPassword = eKey.decrypt(encryptedPassword);
        assertEquals(password, decryptedPassword);
    }

    @Test
   public void testServiceCenterAlerted(){
        ServiceCenter serviceCenter = new ServiceCenter();
        new CentralControlUnit("ZooxSDC73");
        EmergencyButton emergencyButton = new EmergencyButton(new OpenDoorCommand(new Door(false)), serviceCenter);
        emergencyButton.pressButton();
        assertTrue(serviceCenter.isAlerted());
    }
}

