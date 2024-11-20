package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smarthome.SmartTV;

class SmartTVTest {

    private SmartTV smartTV;

    @BeforeEach
    void setUp() {
        smartTV = new SmartTV();
    }
    
    
 // test for PowerOnOrNot

    @Test 
    void testPowerOn_Or_Not() {
//    	smartTV.turnOn();
    	assertTrue("Please turn On first ",smartTV.isPoweredOn());
    }
 
    @Test
    void testTurnOn() {
//    	assertTrue(smartTV.isOn());
        smartTV.turnOn();
        assertTrue(smartTV.isOn());
    }

    @Test
    void testTurnOff() {
    	smartTV.turnOn();
    	smartTV.turnOff();
        assertFalse(smartTV.isOn());
    }
    
    
// test Network

    @Test
    void testNetworkConnection() {
    	smartTV.turnOn();
    	smartTV.connectToWiFi("Harsh");
        assertTrue(smartTV.isConnected());
    }
    
// test temperature
    
    @Test
    void testSetTemperature() {
    	smartTV.turnOn();
    	smartTV.connectToWiFi("Harsh");
    	smartTV.setTemperature(72);
        assertEquals(72, smartTV.getTemperature());
    }

    @Test
    void testAdjustTemperature() {
    	smartTV.turnOn();
    	smartTV.connectToWiFi("Harsh");
    	smartTV.setTemperature(70);
    	smartTV.increaseTemperature(2); // Increase by 2 degrees
        assertEquals(72, smartTV.getTemperature());
        smartTV.decreaseTemperature(2);  // decrease by 2 degrees
    }
    
  
// test Volume
    @Test
    void testVolume() {
    	smartTV.turnOn();
    	smartTV.connectToWiFi("Harsh");
    	smartTV.adjustVolume(5);
    	assertEquals(5, smartTV.getVolume());
    }
    
    
    
    
}
