package observer;

import autonomousVehicle.body.Door;
import status.ISensor;

public class DoorButton implements ISensor {
    private Door door;

    public DoorButton(Door door) {
        this.door = door;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    @Override
    public void OpenCloseDoor() {
        door.setOpen(!door.isOpen());
    }
}
