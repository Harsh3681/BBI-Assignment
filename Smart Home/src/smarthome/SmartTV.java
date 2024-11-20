package smarthome;

import java.util.function.BooleanSupplier;

public class SmartTV implements PowerControl, NetworkConnected, AudioControl, TemperatureControl {
	
    private boolean power;
    private boolean connected;
    private int temperature;
    private int volume;
    private boolean muted=false;
    
    
	public boolean isOn() {
		return power;
	}
    
    @Override
    public void turnOn() {
        power = true;
        System.out.println("Smart TV is now ON.");
    }

    @Override
    public void turnOff() {
    	if(!power) {
    		System.out.println("Please turn on TV");
    	}else {
    		power = false;
    		System.out.println("Smart TV is now OFF.");	
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
            System.out.println("Smart TV connected to Wi-Fi: " + network);
        } else {
            System.out.println("Cannot connect to Wi-Fi. Turn on the Smart TV first.");
        }
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    

    @Override
    public void adjustVolume(int volume) {
        if (muted) {
            System.out.println("Unmute the Smart TV before adjusting the volume.");
        } else if (power && connected) {
            this.volume = volume;
            System.out.println("Volume set to: " + volume);
        } else if (!power) {
            System.out.println("Turn on the Smart TV first.");
        } else {
            System.out.println("Connect to Wi-Fi before adjusting the volume.");
        }
    }

    @Override
    public void mute() {
    	if (power && connected && !muted) {
    		muted = true;
    		System.out.println("Smart Speaker is now muted.");
        } else if (!power) {
            System.out.println("Turn on the Smart TV first.");
        } else {
            System.out.println("Connect to Wi-Fi before mute TV.");
        }
    }
    
    @Override
	public void Unmute() {
		if (power && connected && muted) {
    		muted = false;
    		System.out.println("Smart Speaker is now Unmuted.");
        } else if (!power) {
            System.out.println("Turn on the Smart TV first.");
        } else {
            System.out.println("Connect to Wi-Fi before mute TV.");
        }
	}

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public boolean isMuted() {
        return muted;
    }

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		if (power && connected) {
			this.temperature = temperature;
        } else if (!power) {
            System.out.println("Turn on the Smart TV first.");
        } else {
            System.out.println("Connect to Wi-Fi before adjusting the volume.");
        }
		
	}

	@Override
	public void increaseTemperature(int i) {
		if (power && connected) {
			this.temperature = temperature + i;
        } else if (!power) {
            System.out.println("Turn on the Smart TV first.");
        } else {
            System.out.println("Connect to Wi-Fi before adjusting the volume.");
        }	
	}

	@Override
	public void decreaseTemperature(int i) {
		if (power && connected) {
			this.temperature = temperature - i;
        } else if (!power) {
            System.out.println("Turn on the Smart TV first.");
        } else {
            System.out.println("Connect to Wi-Fi before adjusting the volume.");
        }
	}

	

	

	

	
}

