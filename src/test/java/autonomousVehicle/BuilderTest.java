package autonomousVehicle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BuilderTest {

    @Test
    void TestBuilding(){
        AutonomousVehicle av = new AutonomousVehicle.Builder().
                Battery().
                Camera().
                GPS().
                Lidar().
                Chassis().
                ElectricEngine().
                LEDHeadlight().
                BrakeLight().
                Indicator().
                Door().
                Seat().
                Wheel().
                Brake().
                build();

        assertNotNull(av.getBattery());
        assertNotNull(av.getCamera());
        assertNotNull(av.getGps());
        assertNotNull(av.getLidar());
        assertNotNull(av.getChassis());
        assertNotNull(av.getElectricEngine());
        assertNotNull(av.getLedHeadLight());
        assertNotNull(av.getBrakeLight());
        assertNotNull(av.getIndicator());
        assertNotNull(av.getDoor());
        assertNotNull(av.getSeat());
        assertNotNull(av.getWheel());
        assertNotNull(av.getBrake());


    }

}