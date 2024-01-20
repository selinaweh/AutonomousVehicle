package command;

public class ServiceCenter {

    private boolean isAlerted = false;

    public ServiceCenter(){
    }
    public void alert() {
        this.isAlerted = true;
        System.out.println("Emergency alert received at the service center.");
    }

    public boolean isAlerted() {
        return isAlerted;
    }
}
