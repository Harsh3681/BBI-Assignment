package smarthome;

import java.util.Scanner;


public class SmartHomeHub {

	public static void controlThermostat(SmartThermostat thermostat, Scanner scanner) {
        System.out.println("\n--- Smart Thermostat ---");
        System.out.println("1. Turn On");
        System.out.println("2. Turn Off");
        System.out.println("3. Connect to Wi-Fi");
        System.out.println("4. Set Temperature");
        System.out.println("5. Get Current Temperature");
        System.out.print("Choose an action: ");
        int action = scanner.nextInt();

        switch (action) {
            case 1 -> thermostat.turnOn();
            case 2 -> thermostat.turnOff();
            case 3 -> {
                System.out.print("Enter Wi-Fi name: ");
                scanner.nextLine(); // Consume newline
                String wifi = scanner.nextLine();
                thermostat.connectToWiFi(wifi);
            }
            case 4 -> {
                System.out.print("Enter temperature: ");
                int temp = scanner.nextInt();
                thermostat.setTemperature(temp);
            }
            case 5 -> {
                if (thermostat.isPoweredOn() && thermostat.isConnected()) {
                    System.out.println("Current Temperature: " + thermostat.getTemperature() + "°C.");
                } else {
                    System.out.println("Thermostat must be powered on and connected to Wi-Fi to retrieve temperature.");
                }
            }
            default -> System.out.println("Invalid action.");
        }
    }

    public static void controlSpeaker(SmartSpeaker speaker, Scanner scanner) {
        System.out.println("\n--- Smart Speaker ---");
        System.out.println("1. Turn On");
        System.out.println("2. Turn Off");
        System.out.println("3. Connect to Wi-Fi");
        System.out.println("4. Adjust Volume");
        System.out.println("5. Mute");
        System.out.println("6. Un-mute");
        System.out.println("7. Check Current Volume");
        System.out.print("Choose an action: ");
        int action = scanner.nextInt();

        switch (action) {
            case 1 -> speaker.turnOn();
            case 2 -> speaker.turnOff();
            case 3 -> {
                System.out.print("Enter Wi-Fi name: ");
                scanner.nextLine(); // Consume newline
                String wifi = scanner.nextLine();
                speaker.connectToWiFi(wifi);
            }
            case 4 -> {
                System.out.print("Enter volume: ");
                int volume = scanner.nextInt();
                speaker.adjustVolume(volume);
            }
            case 5 -> speaker.mute();
            case 6 -> speaker.Unmute();
            case 7 -> {
                if (speaker.isPoweredOn() && speaker.isConnected()) {
                    if (speaker.isMuted()) {
                        System.out.println("Speaker is muted. Volume is 0.");
                    } else {
                        System.out.println("Current Volume: " + speaker.getVolume());
                    }
                } else {
                    System.out.println("Speaker must be powered on and connected to Wi-Fi to check volume.");
                }
            }
            default -> System.out.println("Invalid action.");
        }
    }

    public static void controlTV(SmartTV tv, Scanner scanner) {
        System.out.println("\n--- Smart TV ---");
        System.out.println("1. Turn On");
        System.out.println("2. Turn Off");
        System.out.println("3. Connect to Wi-Fi");
        System.out.println("4. Adjust Volume");
        System.out.println("5. Mute");
        System.out.println("6. Un-mute");
        System.out.println("7. Get Current Volume");
        System.out.println("8. Set Temperature");
        System.out.println("9. Get Temperature");
        System.out.print("Choose an action: ");
        int action = scanner.nextInt();

        switch (action) {
            case 1 -> tv.turnOn();
            case 2 -> tv.turnOff();
            case 3 -> {
                System.out.print("Enter Wi-Fi name: ");
                scanner.nextLine(); // Consume newline
                String wifi = scanner.nextLine();
                tv.connectToWiFi(wifi);
            }
            case 4 -> {
                System.out.print("Enter volume: ");
                int volume = scanner.nextInt();
                tv.adjustVolume(volume);
            }
            case 5 -> tv.mute();
            case 6 -> tv.Unmute();
            case 7 -> {
            	if (tv.isPoweredOn() && tv.isConnected()) {
                    if (tv.isMuted()) {
                        System.out.println("TV is muted. Volume is 0.");
                    } else {
                        System.out.println("Current Volume: " + tv.getVolume());
                    }
                } else {
                    System.out.println("TV must be powered on and connected to Wi-Fi to check volume.");
                }
            }
            
            case 8 -> {
            	System.out.print("Enter temperature : ");
                int temp = scanner.nextInt();
                tv.setTemperature(temp);
            }
            
            case 9 -> {
            	if (tv.isPoweredOn() && tv.isConnected()) {
            		System.out.println("Current temperature : " + tv.getTemperature());
            	}else {
                    System.out.println("TV must be powered on and connected to Wi-Fi to check volume.");
                }
            }
            
            default -> System.out.println("Invalid action.");
        }
    }
}




