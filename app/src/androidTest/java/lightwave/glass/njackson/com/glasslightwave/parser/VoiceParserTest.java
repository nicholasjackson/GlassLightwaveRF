package lightwave.glass.njackson.com.glasslightwave.parser;

import android.test.suitebuilder.annotation.SmallTest;

import com.njackson.glass.lightwave.parser.VoiceParser;
import com.njackson.glass.lightwave.parser.VoiceParserException;

import junit.framework.TestCase;

/**
 * Created by server on 30/06/2014.
 */
public class VoiceParserTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @SmallTest
    public void testVoiceParserGetsActionON() throws VoiceParserException {
        VoiceParser parser = new VoiceParser("turn kitchen lights on");
        assertEquals(VoiceParser.Action.ON,parser.get_action());
    }

    @SmallTest
    public void testVoiceParserGetsActionOFF() throws VoiceParserException {
        VoiceParser parser = new VoiceParser("turn kitchen lights off");
        assertEquals(VoiceParser.Action.OFF,parser.get_action());
    }

    @SmallTest
    public void testVoiceParserGetsActionREGISTER_GLASS() throws VoiceParserException {
        VoiceParser parser = new VoiceParser("register glass");
        assertEquals(VoiceParser.Action.REGISTER_GLASS,parser.get_action());
    }

    @SmallTest
    public void testVoiceParserThrowsExceptionWhenNoActionFound() {
        VoiceParserException exception = null;
        try {
            VoiceParser parser = new VoiceParser("turn kitchen lights aaabab");
        } catch(VoiceParserException vpe) {
            exception = vpe;
        }
        assertNotNull("Expected exception to be thrown", exception);
    }

    @SmallTest
    public void testVoiceParserGetsRoomBED_ROOM() throws VoiceParserException {
        VoiceParser parser = new VoiceParser("turn bed room lights off");
        assertEquals(VoiceParser.Room.BED_ROOM,parser.get_room());
    }

    @SmallTest
    public void testVoiceParserThrowsExceptionWhenNoRoomFound() {
        VoiceParserException exception = null;
        try {
            VoiceParser parser = new VoiceParser("turn ssdsd lights on");
        } catch(VoiceParserException vpe) {
            exception = vpe;
        }
        assertNotNull("Expected exception to be thrown", exception);
    }

    @SmallTest
    public void testVoiceParserGetsDeviceLights() throws VoiceParserException {
        VoiceParser parser = new VoiceParser("turn bed room lights off");
        assertEquals(VoiceParser.Device.LIGHTS,parser.get_device());
    }

    @SmallTest
    public void testVoiceParserThrowsExceptionWhenNoDeviceFound() {
        VoiceParserException exception = null;
        try {
            VoiceParser parser = new VoiceParser("turn bed room sdsd on");
        } catch(VoiceParserException vpe) {
            exception = vpe;
        }
        assertNotNull("Expected exception to be thrown", exception);
    }

}
