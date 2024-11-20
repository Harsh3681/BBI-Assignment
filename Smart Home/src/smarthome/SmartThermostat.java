package smarthome;


public class SmartThermostat implements PowerControl, NetworkConnected, TemperatureControl {
    private boolean power;
    private boolean connected;
    private int temperature;

    
    
    @Override
    public void turnOn() {
        power = true;
        System.out.println("Smart Thermostat is now ON.");
    }

    @Override
    public void turnOff() {
        if(!power) {
    		System.out.println("Please turn on TV");
    	}else {
    		power = false;
    		System.out.println("Smart Thermostat is now OFF.");	
    	}
    }

    @Override
    public boolean isPoweredOn() {
        return power;
    }

    @Override
    public void connectToWiFi(String network) {
        if (power) {
            connected = true;
            System.out.println("Smart Thermostat connected to Wi-Fi: " + network);
        } else {
            System.out.println("Cannot connect to Wi-Fi. Turn on the Smart Thermostat first.");
        }
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void setTemperature(int temperature) {
        if (power && connected) {
            this.temperature = temperature;
            System.out.println("Temperature set to: " + temperature + "°C.");
        } else if (!power) {
            System.out.println("Turn on the Smart Thermostat first.");
        } else {
            System.out.println("Connect to Wi-Fi before setting the temperature.");
        }
    }

    @Override
    public int getTemperature() {
        return temperature;
    }

  // i done this below for testing purpose
	@Override
	public void increaseTemperature(int i) {
		this.temperature = this.temperature + i;
		System.out.println("inc "+temperature);
	}

	@Override
	public void decreaseTemperature(int i) {
		this.temperature = this.temperature - i;
		System.out.println("dec "+temperature);
	}

}