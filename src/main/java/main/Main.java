package main;

import autonomousVehicle.lights.brakeLight.BrakeLight;
import autonomousVehicle.centralUnit.CentralUnit;


public class Main {
    public static void main(String[] args) {

        var engineType = Configuration.INSTANCE.engineType;

        CentralUnit centralUnit = new CentralUnit();
        centralUnit.addSubscriber(new BrakeLight());
    }
}