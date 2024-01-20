package autonomousVehicle.centralUnit;
import com.google.common.eventbus.EventBus;

import events.brake.*;
import events.camera.*;

import events.electricEngine.*;
import events.gps.*;
import events.lidar.*;
import events.lights.brakeLight.*;
import events.lights.indicator.*;

import events.lights.ledHeadlight.*;
import exceptions.InvalidVehicleStateException;


public class CentralUnit implements ICentralUnit{
    private final EventBus eventBus;
    private boolean isEngineOn = false;
    private boolean isMoving = false;

    public CentralUnit(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    @Override
    public void startup() {
        isEngineOn = true;
        eventBus.post(new EventEngineOn());
        eventBus.post(new EventLEDOn());
        eventBus.post(new EventGPSConnectSatellite("118.75"));
        eventBus.post(new EventCameraOn());
        eventBus.post(new EventLidarOn());
        eventBus.post(new EventLeftIndicatorOn());
        System.out.println("Startup");
    }

    @Override
    public void move(int deltaRPM, int seconds) {
        if (!isEngineOn) {
            throw new InvalidVehicleStateException("Cannot move: Engine is off");
        }
        isMoving = true;
        eventBus.post(new EventLeftIndicatorOff());
        eventBus.post(new EventRightIndicatorOff());
        eventBus.post(new EventLEDDimmed());
        eventBus.post(new EventIncreaseRPM(deltaRPM, seconds));
        eventBus.post(new EventBrakeSet(0));
        eventBus.post(new EventBrakeLightOff());
        System.out.println("Moving");
    }

    @Override
    public void leftTurn(int deltaRPM, int seconds) {
        if (!isEngineOn || !isMoving) {
            throw new InvalidVehicleStateException("Cannot turn left: Engine is off or vehicle is not moving");
        }
        eventBus.post(new EventLeftIndicatorOn());
        eventBus.post(new EventDecreaseRPM(deltaRPM, seconds));
        eventBus.post(new EventBrakeSet(70));
        eventBus.post(new EventBrakeLightOn());
        System.out.println("Left turn");
    }

    @Override
    public void rightTurn(int deltaRPM, int seconds) {
        if (!isEngineOn || !isMoving) {
            throw new InvalidVehicleStateException("Cannot turn right: Engine is off or vehicle is not moving");
        }
        eventBus.post(new EventRightIndicatorOn());
        eventBus.post(new EventDecreaseRPM(deltaRPM, seconds));
        eventBus.post(new EventBrakeSet(70));
        eventBus.post(new EventBrakeLightOn());
        System.out.println("Right turn");
    }

    @Override
    public void stop() {
        if (!isEngineOn) {
            throw new InvalidVehicleStateException("Cannot stop: Engine is off");
        }
        isMoving = false;
        eventBus.post(new EventBrakeSet(100));
        eventBus.post(new EventBrakeLightOn());
        System.out.println("Car stopped");
    }

    @Override
    public void emergencyStop() {
        if (!isEngineOn) {
            throw new InvalidVehicleStateException("Cannot stop: Engine is off");
        }
        isMoving = false;
        eventBus.post(new EventBrakeSet(100));
        eventBus.post(new EventBrakeLightOn());
        eventBus.post(new EventHazardWarningOn());
        eventBus.post(new EventEngineOff());
        eventBus.post(new EventLEDHighBeam());
        eventBus.post((new EventCameraOff()));
        eventBus.post(new EventLidarOff());
        System.out.println("Emergency stop");
    }

    @Override
    public void shutdown() {
        if (isMoving) {
            throw new InvalidVehicleStateException("Cannot shutdown: Vehicle is still moving");
        }
        isEngineOn = false;
        eventBus.post(new EventBrakeSet(100));
        eventBus.post(new EventEngineOff());
        eventBus.post(new EventBrakeLightOff());
        eventBus.post(new EventLEDOff());
        eventBus.post(new EventHazardWarningOff());
        eventBus.post((new EventGPSOff()));
        eventBus.post(new EventCameraOff());
        eventBus.post(new EventLidarOff());
        System.out.println("Shutdown");
    }
}
