package autonomousVehicle;

public class AutonomousVehicle {

    public static class Builder {

        public Builder Chassis(){
            return this;
        }

        public Builder ElectricEngine(){
            return this;
        }

        public Builder Battery(){
            return this;
        }

        public Builder LEDHeadLight(){
            return this;
        }

        public Builder BrakeLight(){
            return this;
        }

        public Builder Indicator(){
            return this;
        }

        public Builder Door(){
            return this;
        }

        public Builder Seat(){
            return this;
        }

        public Builder Wheel(){
            return this;
        }

        public Builder Break(){
            return this;
        }

        public Builder GPS(){
            return this;
        }

        public Builder Camera(){
            return this;
        }

        public Builder Lidar(){
            return this;
        }

        public AutonomousVehicle build(){
            return new AutonomousVehicle(this);
        }

    }

    private AutonomousVehicle(Builder builder){

    }

}
