package smarthome;


public interface TemperatureControl {
    void setTemperature(int temperature);
    int getTemperature();
	void increaseTemperature(int i);
	void decreaseTemperature(int i);
}