package smarthome;


public interface NetworkConnected {
    void connectToWiFi(String network);
    boolean isConnected();
}