package command;

public class EmergencyButton {
    ICommand command;
    ServiceCenter serviceCenter;

    public EmergencyButton(ICommand command, ServiceCenter serviceCenter){
        this.command = command;
        this.serviceCenter = serviceCenter;
    }

    public void pressButton(){
        command.execute();
        serviceCenter.alert();
    }
}