package smarthome;


public interface PowerControl {
    void turnOn();
    void turnOff();
    boolean isPoweredOn();
}