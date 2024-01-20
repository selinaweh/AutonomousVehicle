package command;

import autonomousVehicle.body.Door;

public class OpenDoorCommand implements ICommand{
    Door door;

    public OpenDoorCommand(Door door){
        this.door = door;
    }

    @Override
    public void execute() {
        door.setOpen(true);
    }
}
