package main;

import autonomousVehicle.AutonomousVehicle;
import memento.Config;
import menu.Menu;

import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        Map<String, String> state = new HashMap<>();

        state.put("rejectDrunkenPassenger", "true");
        state.put("stopByPoliceRequest", "true");
        state.put("allowDriveByNight", "true");
        state.put("behaviorWithNaggingPassengers", "stopAndWaitForExcuse");
        state.put("musicDuringDrive", "ac/dc");

        Config config = new Config(state);

        Menu menu = new Menu(config);

        AutonomousVehicle autonomousVehicle = new AutonomousVehicle.Builder()
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


        autonomousVehicle.getBattery().PlugIn();
        autonomousVehicle.startSimulation();
    }
}

