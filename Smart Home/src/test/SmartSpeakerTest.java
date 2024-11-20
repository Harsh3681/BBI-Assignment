package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smarthome.SmartSpeaker;

class SmartSpeakerTest {

    private SmartSpeaker smartSpeaker;

    @BeforeEach
    void setUp() {
        smartSpeaker = new SmartSpeaker();
    }

// test for AudioCheck
    @Test 
    void testAdjustVolume() {
    	smartSpeaker.turnOn();
    	smartSpeaker.connectToWiFi("Harsh");
    	smartSpeaker.adjustVolume(5);      
        assertEquals(5, smartSpeaker.getVolume());
    }
    
    @Test 
    void testMuteOrNot() {
    	smartSpeaker.turnOn();
    	smartSpeaker.connectToWiFi("Harsh");
//    	smartSpeaker.mute();
    	assertFalse("Speaker not muted !" ,smartSpeaker.isMuted());
    }
    
    
// test for PowerOnOrNot
    @Test 
    void testPowerOn_Or_Not() {
//    	smartSpeaker.turnOn();
    	assertTrue("Please turn On first ",smartSpeaker.isPoweredOn());
    }

// test for Network
    @Test 
    void testNetworkConnected() {
    	smartSpeaker.turnOn();
    	smartSpeaker.connectToWiFi("Harsh");
    	assertTrue(smartSpeaker.isConnected());
    }
    
 
    
}

