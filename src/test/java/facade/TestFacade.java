package facade;
import autonomousVehicle.AutonomousVehicle;
import autonomousVehicle.brake.Brake;
import autonomousVehicle.gps.GPS;
import autonomousVehicle.lidar.Lidar;
import autonomousVehicle.lights.brakeLight.BrakeLight;
import autonomousVehicle.lights.indicator.Indicator;
import autonomousVehicle.lights.indicator.Position;
import autonomousVehicle.lights.ledHeadlight.LEDHeadlight;
import events.brake.EventBrakeSet;
import events.gps.EventGPSConnectSatellite;
import events.lidar.EventLidarOn;
import events.lights.brakeLight.EventBrakeLightOn;
import events.lights.indicator.EventLeftIndicatorOn;
import events.lights.ledHeadlight.EventLEDOn;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
public class TestFacade {
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

    @Test
    public void testStartup() throws Exception{
        autonomousVehicle.getCentralUnit().startup();
        // engine on
        assertTrue(autonomousVehicle.getElectricEngine().isOn());
        // ledHeadlight on
        for (LEDHeadlight ledHeadlight : autonomousVehicle.getLedHeadLight()) {
            assertTrue(ledHeadlight.isLEDOn());
        }
        // gps connected
        for (GPS gps : autonomousVehicle.getGps()) {
            assertEquals("118.75", gps.getFrequency());
        }
        // camera on
        for (Object cameraPort : autonomousVehicle.getCameraPorts()) {
            Method isOnMethod = cameraPort.getClass().getMethod("isOn");
            boolean isOn = (boolean) isOnMethod.invoke(cameraPort);
            assertTrue(isOn);
        }
        // lidar on
        for (Lidar lidar : autonomousVehicle.getLidar()) {
            assertTrue(lidar.isOn());
        }
        // left indicator on
        for (Indicator indicator : autonomousVehicle.getIndicator()) {
            if (indicator.getIndicatorLocation() == Position.LEFT)
                assertTrue(indicator.isLeftIndicatorOn());
        }
    }

    @Test
    public void testMove(){
        autonomousVehicle.getCentralUnit().startup();
        autonomousVehicle.getCentralUnit().move(50,1);
        // indicator left and right off
        for (Indicator indicator : autonomousVehicle.getIndicator()) {
            assertFalse(indicator.isLeftIndicatorOn());
            assertFalse(indicator.isRightIndicatorOn());
        }
        // led dimmed
        for (LEDHeadlight ledHeadlight : autonomousVehicle.getLedHeadLight()) {
            assertTrue(ledHeadlight.isLEDDimmed());
        }
        // rpm 50
        assertEquals(50, autonomousVehicle.getElectricEngine().getCurrentRPM());
        // brake set 0
        for (Brake brake : autonomousVehicle.getBrake()) {
            assertEquals(0, brake.getPercentage());
        }
        // brake light off
        for (BrakeLight brakeLight : autonomousVehicle.getBrakeLight()) {
            assertFalse(brakeLight.isOn());
        }
    }

    @Test
    public void testLeftTurn(){
        autonomousVehicle.getCentralUnit().startup();
        autonomousVehicle.getCentralUnit().move(50,1);
        autonomousVehicle.getCentralUnit().leftTurn(20,1);
        // indicator left on
        for (Indicator indicator : autonomousVehicle.getIndicator()) {
            if (indicator.getIndicatorLocation() == Position.LEFT)
                assertTrue(indicator.isLeftIndicatorOn());
        }
        // rpm 50
        assertEquals(30, autonomousVehicle.getElectricEngine().getCurrentRPM());
        // brake set 70
        for (Brake brake : autonomousVehicle.getBrake()) {
            assertEquals(70, brake.getPercentage());
        }
        // brake light on
        for (BrakeLight brakeLight : autonomousVehicle.getBrakeLight()) {
            assertTrue(brakeLight.isOn());
        }
    }

    @Test
    public void testRightTurn(){
        autonomousVehicle.getCentralUnit().startup();
        autonomousVehicle.getCentralUnit().move(50,1);
        autonomousVehicle.getCentralUnit().rightTurn(30,1);
        // indicator right on
        for (Indicator indicator : autonomousVehicle.getIndicator()) {
            if (indicator.getIndicatorLocation() == Position.RIGHT)
                assertTrue(indicator.isRightIndicatorOn());
        }
        // rpm 50
        assertEquals(20, autonomousVehicle.getElectricEngine().getCurrentRPM());
        // brake set 70
        for (Brake brake : autonomousVehicle.getBrake()) {
            assertEquals(70, brake.getPercentage());
        }
        // brake light on
        for (BrakeLight brakeLight : autonomousVehicle.getBrakeLight()) {
            assertTrue(brakeLight.isOn());
        }
    }

    @Test
    public void testStop(){
        autonomousVehicle.getCentralUnit().startup();
        autonomousVehicle.getCentralUnit().move(50,1);
        autonomousVehicle.getCentralUnit().stop();
        // rpm 0
        assertEquals(0, autonomousVehicle.getElectricEngine().getCurrentRPM());
        // brake set 100
        for (Brake brake : autonomousVehicle.getBrake()) {
            assertEquals(100, brake.getPercentage());
        }
        // brake light on
        for (BrakeLight brakeLight : autonomousVehicle.getBrakeLight()) {
            assertTrue(brakeLight.isOn());
        }
    }

    @Test
    public void testEmergencyStop() throws Exception{
        autonomousVehicle.getCentralUnit().startup();
        autonomousVehicle.getCentralUnit().move(50,1);
        autonomousVehicle.getCentralUnit().emergencyStop();
        // rpm 0
        assertEquals(0, autonomousVehicle.getElectricEngine().getCurrentRPM());
        // brake set 100
        for (Brake brake : autonomousVehicle.getBrake()) {
            assertEquals(100, brake.getPercentage());
        }
        // brake light on
        for (BrakeLight brakeLight : autonomousVehicle.getBrakeLight()) {
            assertTrue(brakeLight.isOn());
        }
        // hazard warning on
        for (Indicator indicator : autonomousVehicle.getIndicator()) {
            assertTrue(indicator.isLeftIndicatorOn());
            assertTrue(indicator.isRightIndicatorOn());
        }
        // led high beam
        for (LEDHeadlight ledHeadlight : autonomousVehicle.getLedHeadLight()) {
            assertTrue(ledHeadlight.isLEDHighBeam());
        }
        // lidar off
        for (Lidar lidar : autonomousVehicle.getLidar()) {
            assertFalse(lidar.isOn());
        }
        // camera off
        for (Object cameraPort : autonomousVehicle.getCameraPorts()) {
            Method isOffMethod = cameraPort.getClass().getMethod("isOn");
            boolean isOn = (boolean) isOffMethod.invoke(cameraPort);
            assertFalse(isOn);
        }
        // engine off
        assertFalse(autonomousVehicle.getElectricEngine().isOn());
    }
    @Test
    public void testShutdown() throws Exception{
        autonomousVehicle.getCentralUnit().startup();
        autonomousVehicle.getCentralUnit().move(50,1);
        autonomousVehicle.getCentralUnit().stop();
        autonomousVehicle.getCentralUnit().shutdown();
        // rpm 0
        assertEquals(0, autonomousVehicle.getElectricEngine().getCurrentRPM());
        // brake set 100
        for (Brake brake : autonomousVehicle.getBrake()) {
            assertEquals(100, brake.getPercentage());
        }
        // brake light on
        for (BrakeLight brakeLight : autonomousVehicle.getBrakeLight()) {
            assertFalse(brakeLight.isOn());
        }
        // hazard warning off
        for (Indicator indicator : autonomousVehicle.getIndicator()) {
            assertFalse(indicator.isLeftIndicatorOn());
            assertFalse(indicator.isRightIndicatorOn());
        }
        // led high beam
        for (LEDHeadlight ledHeadlight : autonomousVehicle.getLedHeadLight()) {
            assertFalse(ledHeadlight.isLEDOn());
        }
        // lidar off
        for (Lidar lidar : autonomousVehicle.getLidar()) {
            assertFalse(lidar.isOn());
        }
        // camera off
        for (Object cameraPort : autonomousVehicle.getCameraPorts()) {
            Method isOffMethod = cameraPort.getClass().getMethod("isOn");
            boolean isOn = (boolean) isOffMethod.invoke(cameraPort);
            assertFalse(isOn);
        }
        // engine off
        assertFalse(autonomousVehicle.getElectricEngine().isOn());

        // gps off
        for (GPS gps : autonomousVehicle.getGps()) {
            assertFalse(gps.isOn());
        }

    }
}
