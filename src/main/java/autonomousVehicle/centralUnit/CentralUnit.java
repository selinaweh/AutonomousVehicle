package autonomousVehicle.centralUnit;
import autonomousVehicle.Subscriber;
import com.google.common.eventbus.EventBus;

import events.brake.*;
import events.camera.*;

import events.electricEngine.*;
import events.gps.*;
import events.lidar.*;
import events.lights.brakeLight.*;
import events.lights.indicator.*;

import events.lights.ledHeadlight.*;


public class CentralUnit implements ICentralUnit{
    private final EventBus eventBus;

    public CentralUnit(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    @Override
    public void startup() {
        eventBus.post(new EventEngineOn());
        eventBus.post(new EventLEDOn());
        eventBus.post(new EventGPSConnectSatellite("118.75"));
        eventBus.post(new EventCameraOn());
        eventBus.post(new EventLidarOn());
        eventBus.post(new EventLeftIndicatorOn());
    }

    @Override
    public void move(int deltaRPM, int seconds) {
        eventBus.post(new EventLeftIndicatorOff());
        eventBus.post(new EventRightIndicatorOff());
        eventBus.post(new EventLEDDimmed());
        eventBus.post(new EventIncreaseRPM(deltaRPM, seconds));
        eventBus.post(new EventBrakeSet(0));
        eventBus.post(new EventBrakeLightOff());
    }

    @Override
    public void leftTurn(int deltaRPM, int seconds) {
        eventBus.post(new EventLeftIndicatorOn());
        eventBus.post(new EventDecreaseRPM(deltaRPM, seconds));
        eventBus.post(new EventBrakeSet(70));
        eventBus.post(new EventBrakeLightOn());
    }

    @Override
    public void rightTurn(int deltaRPM, int seconds) {
        eventBus.post(new EventRightIndicatorOn());
        eventBus.post(new EventDecreaseRPM(deltaRPM, seconds));
        eventBus.post(new EventBrakeSet(70));
        eventBus.post(new EventBrakeLightOn());
    }

    @Override
    public void stop() {
        eventBus.post(new EventBrakeSet(100));
        eventBus.post(new EventBrakeLightOn());
    }

    @Override
    public void emergencyStop() {
        eventBus.post(new EventBrakeSet(100));
        eventBus.post(new EventBrakeLightOn());
        eventBus.post(new EventHazardWarningOn());
        eventBus.post(new EventEngineOff());
        eventBus.post(new EventLEDHighBeam());
        eventBus.post((new EventCameraOff()));
        eventBus.post(new EventLidarOff());
    }

    @Override
    public void shutdown() {
        eventBus.post(new EventBrakeSet(100));
        eventBus.post(new EventEngineOff());
        eventBus.post(new EventBrakeLightOff());
        eventBus.post(new EventLEDOff());
        eventBus.post(new EventHazardWarningOff());
        eventBus.post((new EventGPSOff()));
        eventBus.post(new EventCameraOff());
        eventBus.post(new EventLidarOff());
    }
}
