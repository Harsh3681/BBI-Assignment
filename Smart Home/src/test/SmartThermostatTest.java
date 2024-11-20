package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smarthome.SmartThermostat;


class SmartThermostatTest {

    private SmartThermostat thermostat;

    @BeforeEach
    void setUp() {
        thermostat = new SmartThermostat();
    }

//  testing Power 
    @Test 
    void testPowerOn_Or_Not() {
//    	thermostat.turnOn();
    	assertTrue("Please turn On first ",thermostat.isPoweredOn());
    }
    
//  testing NetworkConnection
    @Test 
    void networkConnect_Or_Not() {
    	thermostat.turnOn();
//    	thermostat.connectToWiFi("Harsh");
    	assertTrue("Please connect to network first ",thermostat.isConnected());
    }
    

//  testing Temperature 
    @Test
    void testSetTemperature() {
    	thermostat.turnOn();
    	thermostat.connectToWiFi("Harsh");
        thermostat.setTemperature(72);
        assertEquals(72, thermostat.getTemperature());
    }

    @Test
    void testAdjustTemperature() {
    	thermostat.turnOn();
    	thermostat.connectToWiFi("Harsh");
        thermostat.setTemperature(70);
        thermostat.increaseTemperature(2); // Increase by 2 degrees
        assertEquals(72, thermostat.getTemperature());
        thermostat.decreaseTemperature(2);  // decrease by 2 degrees
    }
}
