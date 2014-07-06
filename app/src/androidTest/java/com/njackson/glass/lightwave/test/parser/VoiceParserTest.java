package com.njackson.glass.lightwave.test.parser;

import android.test.suitebuilder.annotation.SmallTest;

import com.njackson.glass.lightwave.entities.Config;
import com.njackson.glass.lightwave.parser.VoiceParser;
import com.njackson.glass.lightwave.parser.VoiceParserException;

import junit.framework.TestCase;

/**
 * Created by server on 30/06/2014.
 */
public class VoiceParserTest extends TestCase {

    private final String _json = "{ \"rooms\": [ { \"id\": 1, \"name\": \"Kitchen\", \"lights\": [ { \"name\": \"Chandelier\", \"id\": 1 }, { \"name\": \"Wall Lights\", \"id\": 2 }, { \"name\": \"Spot Lights\", \"id\": 3 } ] },{ \"id\": 2, \"name\": \"Living Room\", \"lights\": [ { \"name\": \"Chandelier\", \"id\": 1 }, { \"name\": \"Hall\", \"id\": 2 } ] },{ \"id\": 3, \"name\": \"Bedroom\", \"lights\": [ { \"name\": \"Chandelier\", \"id\": 1 }, { \"name\": \"Bedside\", \"id\": 2 } ] } ] }";
    private Config _config;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        _config = Config.parseJSON(_json);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @SmallTest
    public void testVoiceParserGetsActionON() throws VoiceParserException {
        VoiceParser parser = new VoiceParser("turn kitchen lights on",_config);
        assertEquals(VoiceParser.Action.ON,parser.get_action());
    }

    @SmallTest
    public void testVoiceParserGetsActionOFF() throws VoiceParserException {
        VoiceParser parser = new VoiceParser("turn kitchen lights off",_config);
        assertEquals(VoiceParser.Action.OFF,parser.get_action());
    }

    @SmallTest
    public void testVoiceParserGetsActionREGISTER_GLASS() throws VoiceParserException {
        VoiceParser parser = new VoiceParser("register glass",_config);
        assertEquals(VoiceParser.Action.REGISTER_GLASS,parser.get_action());
    }

    @SmallTest
    public void testVoiceParserThrowsExceptionWhenNoActionFound() {
        VoiceParserException exception = null;
        try {
            VoiceParser parser = new VoiceParser("turn kitchen lights aaabab",_config);
        } catch(VoiceParserException vpe) {
            exception = vpe;
        }
        assertNotNull("Expected exception to be thrown", exception);
    }

    @SmallTest
    public void testVoiceParserGetsRoomBED_ROOM() throws VoiceParserException {
        VoiceParser parser = new VoiceParser("turn bedroom lights off",_config);
        assertEquals("Bedroom",parser.get_room());
    }

    @SmallTest
    public void testVoiceParserThrowsExceptionWhenNoRoomFound() {
        VoiceParserException exception = null;
        try {
            VoiceParser parser = new VoiceParser("turn ssdsd lights on",_config);
        } catch(VoiceParserException vpe) {
            exception = vpe;
        }
        assertNotNull("Expected exception to be thrown", exception);
    }

    @SmallTest
    public void testVoiceParserGetsDeviceLights() throws VoiceParserException {
        VoiceParser parser = new VoiceParser("turn bedroom lights off",_config);
        assertEquals(VoiceParser.Device.LIGHTS,parser.get_device());
    }

    @SmallTest
    public void testVoiceParserThrowsExceptionWhenNoDeviceFound() {
        VoiceParserException exception = null;
        try {
            VoiceParser parser = new VoiceParser("turn bedroom sdsd on",_config);
        } catch(VoiceParserException vpe) {
            exception = vpe;
        }
        assertNotNull("Expected exception to be thrown", exception);
    }

}
