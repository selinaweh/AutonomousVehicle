package main;

import autonomousVehicle.AutonomousVehicle;
import autonomousVehicle.lights.brakeLight.BrakeLight;
import autonomousVehicle.centralUnit.CentralUnit;


public class Main {
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


        CentralUnit centralUnit = new CentralUnit();
        centralUnit.addSubscriber(new BrakeLight());
    }
}