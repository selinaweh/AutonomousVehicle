package exceptions;

public class InvalidVehicleStateException extends RuntimeException {
    public InvalidVehicleStateException(String message) {
        super(message);
    }
}

