package autonomousVehicle.battery;

public class ChargingStation implements IPlug {

    @Override
    public void PlugIn() {
        Charge();
    }

    private void Charge() {
        System.out.println("Charging...");
    }

}
