package autonomousVehicle.electricEngine;

public class EngineX extends ElectricEngine {
    @Override
    public int CalculateEnergyConsumption(int rpm) {
        return 4 * rpm;
    }
}
