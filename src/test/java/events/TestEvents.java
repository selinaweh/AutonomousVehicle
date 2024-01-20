package events;

import autonomousVehicle.AutonomousVehicle;
import autonomousVehicle.battery.Battery;
import autonomousVehicle.brake.Brake;
import autonomousVehicle.electricEngine.ElectricEngine;
import autonomousVehicle.gps.GPS;
import autonomousVehicle.lidar.Lidar;
import autonomousVehicle.lights.brakeLight.BrakeLight;
import autonomousVehicle.lights.indicator.Indicator;
import autonomousVehicle.lights.indicator.Position;
import autonomousVehicle.lights.ledHeadlight.LEDHeadlight;
import com.google.common.eventbus.EventBus;
import events.brake.EventBrakeSet;
import events.camera.EventCameraOff;
import events.camera.EventCameraOn;
import events.electricEngine.EventDecreaseRPM;
import events.electricEngine.EventEngineOff;
import events.electricEngine.EventEngineOn;
import events.electricEngine.EventIncreaseRPM;
import events.gps.EventGPSConnectSatellite;
import events.gps.EventGPSOff;
import events.gps.EventGPSOn;
import events.lidar.EventLidarOff;
import events.lidar.EventLidarOn;
import events.lights.brakeLight.EventBrakeLightOff;
import events.lights.brakeLight.EventBrakeLightOn;
import events.lights.indicator.*;
import events.lights.ledHeadlight.EventLEDDimmed;
import events.lights.ledHeadlight.EventLEDHighBeam;
import events.lights.ledHeadlight.EventLEDOff;
import events.lights.ledHeadlight.EventLEDOn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class TestEvents {
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

    // test brake events
    @Test
    public void testEventBrakeSetForAllBrakes() {
        for (Brake brake : autonomousVehicle.getBrake()) {
            brake.receive(new EventBrakeSet(50));
            assertEquals(50, brake.getPercentage());
        }
    }

    // test brake light events
    @Test
    public void testEventBrakeLightOnOffForAllBrakeLights() {
        for (BrakeLight brakeLight : autonomousVehicle.getBrakeLight()) {
            brakeLight.receive(new EventBrakeLightOn());
            assertTrue(brakeLight.isOn());
            brakeLight.receive(new EventBrakeLightOff());
            assertFalse(brakeLight.isOn());
        }
    }

    // test indicator events
    @Test
    public void testEventIndicatorLeftForLeftIndicators() {
        for (Indicator indicator : autonomousVehicle.getIndicator()) {
            indicator.receive(new EventLeftIndicatorOn());
            if (indicator.getIndicatorLocation() == Position.LEFT)
                assertTrue(indicator.isLeftIndicatorOn());
            if (indicator.getIndicatorLocation() == Position.RIGHT)
                assertFalse(indicator.isLeftIndicatorOn());
            indicator.receive(new EventLeftIndicatorOff());
            if (indicator.getIndicatorLocation() == Position.LEFT)
                assertFalse(indicator.isLeftIndicatorOn());
            if (indicator.getIndicatorLocation() == Position.RIGHT)
                assertFalse(indicator.isLeftIndicatorOn());
        }
    }

    @Test
    public void testEventIndicatorRightForRightIndicators() {
        for (Indicator indicator : autonomousVehicle.getIndicator()) {
            indicator.receive(new EventRightIndicatorOn());
            if (indicator.getIndicatorLocation() == Position.LEFT)
                assertFalse(indicator.isRightIndicatorOn());
            if (indicator.getIndicatorLocation() == Position.RIGHT)
                assertTrue(indicator.isRightIndicatorOn());
            indicator.receive(new EventRightIndicatorOff());
            if (indicator.getIndicatorLocation() == Position.LEFT)
                assertFalse(indicator.isRightIndicatorOn());
            if (indicator.getIndicatorLocation() == Position.RIGHT)
                assertFalse(indicator.isRightIndicatorOn());
        }
    }

    @Test
    public void testEventIndicatorHazardWarningForAllIndicators() {
        for (Indicator indicator : autonomousVehicle.getIndicator()) {
            indicator.receive(new EventHazardWarningOn());
            assertTrue(indicator.isLeftIndicatorOn());
            assertTrue(indicator.isRightIndicatorOn());
            indicator.receive(new EventHazardWarningOff());
            assertFalse(indicator.isLeftIndicatorOn());
            assertFalse(indicator.isRightIndicatorOn());
        }
    }

    // test led headlight events
    @Test
    public void testEventLEDOnOffForAllLEDHeadlights() {
        for (LEDHeadlight ledHeadlight : autonomousVehicle.getLedHeadLight()) {
            ledHeadlight.receive(new EventLEDOn());
            assertTrue(ledHeadlight.isLEDOn());
            assertFalse(ledHeadlight.isLEDHighBeam());
            assertFalse(ledHeadlight.isLEDDimmed());
            ledHeadlight.receive(new EventLEDOff());
            assertFalse(ledHeadlight.isLEDOn());
            assertFalse(ledHeadlight.isLEDHighBeam());
            assertFalse(ledHeadlight.isLEDDimmed());
        }
    }

    @Test
    public void testEventLEDDimmedForAllLEDHeadlights() {
        for (LEDHeadlight ledHeadlight : autonomousVehicle.getLedHeadLight()) {
            ledHeadlight.receive(new EventLEDOn());
            ledHeadlight.receive(new EventLEDDimmed());
            assertTrue(ledHeadlight.isLEDOn());
            assertFalse(ledHeadlight.isLEDHighBeam());
            assertTrue(ledHeadlight.isLEDDimmed());
        }
    }

    @Test
    public void testEventLEDHighBeamForAllLEDHeadlights() {
        for (LEDHeadlight ledHeadlight : autonomousVehicle.getLedHeadLight()) {
            ledHeadlight.receive(new EventLEDOn());
            ledHeadlight.receive(new EventLEDHighBeam());
            assertTrue(ledHeadlight.isLEDOn());
            assertTrue(ledHeadlight.isLEDHighBeam());
            assertFalse(ledHeadlight.isLEDDimmed());
        }
    }

    // test camera events
    @Test
    public void testEventCameraOnOffForAllCameras() throws Exception {
        EventBus eventBus = autonomousVehicle.getCentralUnit().getEventBus();
        eventBus.post(new EventCameraOn());
        for (Object cameraPort : autonomousVehicle.getCameraPorts()) {
            Method isOnMethod = cameraPort.getClass().getMethod("isOn");
            boolean isOn = (boolean) isOnMethod.invoke(cameraPort);
            assertTrue(isOn);
        }
        eventBus.post(new EventCameraOff());
        for (Object cameraPort : autonomousVehicle.getCameraPorts()) {
            Method isOffMethod = cameraPort.getClass().getMethod("isOn");
            boolean isOn = (boolean) isOffMethod.invoke(cameraPort);
            assertFalse(isOn);
        }
    }

    // test electric engine events
    @Test
    public void testEventEngineOnOffElectricEngine() {
        ElectricEngine electricEngine = autonomousVehicle.getElectricEngine();
        electricEngine.receive(new EventEngineOn());
        assertTrue(electricEngine.isOn());
        electricEngine.receive(new EventEngineOff());
        assertFalse(electricEngine.isOn());
    }

    @Test
    public void testEventEngineIncreaseDecreaseRPM() {
        ElectricEngine electricEngine = autonomousVehicle.getElectricEngine();
        electricEngine.receive(new EventEngineOn());
        electricEngine.receive(new EventIncreaseRPM(100, 1));
        assertEquals(100, electricEngine.getCurrentRPM());
        electricEngine.receive(new EventDecreaseRPM(50, 1));
        assertEquals(50, electricEngine.getCurrentRPM());
        electricEngine.receive(new EventIncreaseRPM(350, 1));
        assertEquals(300, electricEngine.getCurrentRPM());
        electricEngine.receive(new EventDecreaseRPM(350, 1));
        assertEquals(0, electricEngine.getCurrentRPM());
    }

    // test gps events
    @Test
    public void testEventGPSOnOff() {
        for (GPS gps : autonomousVehicle.getGps()) {
            gps.receive(new EventGPSOn());
            assertTrue(gps.isOn());
            gps.receive(new EventGPSOff());
            assertFalse(gps.isOn());
        }
    }

    @Test
    public void testEventGPSConnectSatellite() {
        for (GPS gps : autonomousVehicle.getGps()) {
            gps.receive(new EventGPSConnectSatellite("118.75"));
            assertEquals("118.75", gps.getFrequency());
        }
    }

    // test lidar events
    @Test
    public void testEventLidarOnOff() {
        for (Lidar lidar : autonomousVehicle.getLidar()) {
            lidar.receive(new EventLidarOn());
            assertTrue(lidar.isOn());
            lidar.receive(new EventLidarOff());
            assertFalse(lidar.isOn());
        }
    }

    // test battery events
    @Test
    public void testBatteryEnergyStateChange() {
        Battery battery = autonomousVehicle.getBattery();
        assertEquals(0, battery.getDirectEnergy());
        battery.setEnergy(1);
        assertEquals(1, battery.getDirectEnergy());
    }

    @Test
    public void testBatteryEnergyStateChangeInvalidValue() {
        Battery battery = autonomousVehicle.getBattery();
        battery.setEnergy(5);
        assertEquals(0, battery.getDirectEnergy());
    }

    @Test
    public void testLowBatteryEvent() {
        Battery battery = autonomousVehicle.getBattery();
        battery.setEnergy(0);
        battery.checkAndPostLowBatteryEvent();
    }
}

