package smarthome;


public class SmartSpeaker implements PowerControl, NetworkConnected, AudioControl {
    private boolean power;
    private boolean connected;
    private int volume=0;
    private boolean muted;

    @Override
    public void turnOn() {
        power = true;
        System.out.println("Smart Speaker is now ON.");
    }

    @Override
    public void turnOff() {
        if(!power) {
    		System.out.println("Please turn on TV");
    	}else {
    		power = false;
    		System.out.println("Smart Speaker is now OFF.");	
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
            System.out.println("Smart Speaker connected to Wi-Fi: " + network);
        } else {
            System.out.println("Cannot connect to Wi-Fi. Turn on the Smart Speaker first.");
        }
    }
    

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void adjustVolume(int volume) {
        if (muted) {
            System.out.println("Unmute the Smart Speaker before adjusting the volume.");
        } else if (power && connected) {
            this.volume = volume;
            System.out.println("Volume set to: " + volume);
        } else if (!power) {
            System.out.println("Turn on the Smart Speaker first.");
        } else {
            System.out.println("Connect to Wi-Fi before adjusting the volume.");
        }
    }

    @Override
    public void mute() {
    	if ((power && connected) && !muted) {
    		muted = true;
    		System.out.println("Smart Speaker is now muted.");
        } else if (!power) {
            System.out.println("Turn on the Smart Speaker first.");
        } else {
            System.out.println("Connect to Wi-Fi before mute speaker.");
        }
    }
    
    @Override
	public void Unmute() {
    	if (power && connected && muted) {
    		muted = false;
    		System.out.println("Smart Speaker is now Unmuted.");
        } else if (!power) {
            System.out.println("Turn on the Smart Speaker first.");
        } else {
            System.out.println("Connect to Wi-Fi before mute speaker.");
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

	
}