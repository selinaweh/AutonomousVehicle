package menu;

import memento.Config;
import memento.ConfigCaretaker;

import java.util.Scanner;

public class Menu {
    private final ConfigCaretaker caretaker = new ConfigCaretaker();
    private Config config;

    public Menu(Config config) {
        this.config = config;
        caretaker.setSaveConfig(config.Save());
        MenuLoop();
    }

    private void MenuLoop() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("[i]print");
        System.out.println("[ii] set parameter");
        System.out.println("[iii] undo");
        System.out.println("[iv] exit");

        String input = scanner.nextLine();
        do {
            switch (input) {
                case "i":
                    config.PrintConfig();
                    MenuLoop();
                    config.PrintConfig();
                    break;
                case "ii":
                    SetParameter();
                    config.PrintConfig();
                    MenuLoop();
                    break;
                case "iii":
                    config.Undo(caretaker.getSaveConfig());
                    config.PrintConfig();
                    MenuLoop();
                    break;
                case "iv":
                    System.exit(0);
                    break;
                case "-config":
                    System.out.println("Starting application");
                    break;
                default:
                    System.out.println("Wrong Input [i], [ii], [iii], [iv]");
            }
        }
        while (!input.equals("i") &&
                !input.equals("ii") &&
                !input.equals("iii") &&
                !input.equals("iv") &&
                !input.equals("-config"));
    }

    private void SetParameter() {
        Scanner scanner = new Scanner(System.in);
        String inputId;
        do {
            System.out.println("Give id of Parameter: ");
            inputId = scanner.nextLine();

            switch (inputId) {
                case "rejectDrunkenPassenger", "stopByPoliceRequest", "allowDriveByNight", "behaviorWithNaggingPassengers", "musicDuringDrive":
                    config.SetParameters(inputId, AllowedValues(inputId));
                    break;
                default:
                    System.out.println("Wrong input");

            }
        }
        while (!inputId.equals("rejectDrunkenPassenger") &&
                !inputId.equals("stopByPoliceRequest") &&
                !inputId.equals("allowDriveByNight") &&
                !inputId.equals("behaviorWithNaggingPassengers") &&
                !inputId.equals("musicDuringDrive"));
    }

    private String AllowedValues(String inputId) {
        Scanner scanner = new Scanner(System.in);
        String allowedValues = "";

        switch (inputId) {
            case "rejectDrunkenPassenger", "stopByPoliceRequest", "allowDriveByNight":
                System.out.println("enter value for [" + inputId + "]"
                        + " | current " + config.getState().get("rejectDrunkenPassenger")
                        + " | allowed true | false: ");
                do {
                    allowedValues = scanner.nextLine();
                    switch (allowedValues) {
                        case "true", "false":
                            System.out.println("new value " + allowedValues);
                            break;
                        default:
                            System.out.println("Wrong input");
                    }
                }
                while (!allowedValues.equals("false") &&
                        !allowedValues.equals("true"));
                break;
            case "behaviorWithNaggingPassengers":
                System.out.println("enter value for [behaviorWithNaggingPassengers]"
                        + " | current " + config.getState().get("behaviorWithNaggingPassengers")
                        + " | allowed doNothing | stopAndWaitForExcuse: ");
                do {
                    allowedValues = scanner.nextLine();
                    switch (allowedValues) {
                        case "doNothing", "stopAndWaitForExcuse":
                            System.out.println("new value " + allowedValues);
                            break;
                        default:
                            System.out.println("Wrong input ");
                    }
                }
                while (!allowedValues.equals("doNothing") &&
                        !allowedValues.equals("stopAndWaitForExcuse"));
                break;
            case "musicDuringDrive":
                System.out.println("enter value for [musicDuringDrive]"
                        + " | current " + config.getState().get("musicDuringDrive")
                        + " | allowed ac/dc | Helene Fischer: ");
                do {
                    allowedValues = scanner.nextLine();
                    switch (allowedValues) {
                        case "ac/dc", "Helene Fischer":
                            System.out.println("new value " + allowedValues);
                            break;
                        default:
                            System.out.println("Wrong input");
                    }
                }
                while (!allowedValues.equals("ac/dc") &&
                        !allowedValues.equals("Helene Fischer"));
                break;
        }
        return allowedValues;

    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}
