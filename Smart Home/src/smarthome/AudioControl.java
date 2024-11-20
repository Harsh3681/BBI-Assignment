package smarthome;

public interface AudioControl {
    void adjustVolume(int volume);
    void mute();
    void Unmute();
    int getVolume();
    boolean isMuted();
}