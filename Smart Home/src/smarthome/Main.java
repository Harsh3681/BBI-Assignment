package smarthome;

import java.util.Scanner;

public class Main {
	
	private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SmartThermostat thermostat = new SmartThermostat();
        SmartSpeaker speaker = new SmartSpeaker();
        SmartTV tv = new SmartTV();

        while (true) {
            System.out.println("\n--- Smart Home Hub ---");
            System.out.println("1. Smart Thermostat");
            System.out.println("2. Smart Speaker");
            System.out.println("3. Smart TV");
            System.out.println("4. Exit");
            System.out.print("Choose a device to control: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> SmartHomeHub.controlThermostat(thermostat, scanner);
                case 2 -> SmartHomeHub.controlSpeaker(speaker, scanner);
                case 3 -> SmartHomeHub.controlTV(tv, scanner);
                case 4 -> {
                    System.out.println("Exiting Smart Home Hub. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
	

}