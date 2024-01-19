package main;

import autonomousVehicle.AutonomousVehicle;

public class Application {

    public static void main(String[] args) {

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

