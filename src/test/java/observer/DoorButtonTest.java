package observer;

import autonomousVehicle.body.Door;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoorButtonTest {
    private Door door;
    private DoorButton doorButton;

    @BeforeEach
    public void Init() {
        door = new Door(false);

        doorButton = new DoorButton(door);
    }

    @Test
    void openCloseDoor() {
        doorButton.OpenCloseDoor();

        assertTrue(door.isOpen());

        doorButton.OpenCloseDoor();

        assertFalse(door.isOpen());
    }
}