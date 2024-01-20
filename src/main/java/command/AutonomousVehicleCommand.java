package command;

public class AutonomousVehicleCommand {
    private boolean isActive;
    private String password;

    public AutonomousVehicleCommand(String password){
        this.password = password;
        this.isActive = false;
    }

    public void activate(String encryptedPassword, ElectronicKey eKey) throws Exception{
        String decryptedPassword = eKey.encrypt(this.password);
        if (decryptedPassword.equals(encryptedPassword)){
            this.isActive = !this.isActive;
        }
    }

    public boolean isActive(){
        return this.isActive;
    }
}

