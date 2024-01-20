package command;

import autonomousVehicle.body.Door;

public class CloseDoorCommand implements ICommand {
    Door door;

    public CloseDoorCommand(Door door){
        this.door = door;
    }

    @Override
    public void execute() {
        door.setOpen(false);
    }
}
