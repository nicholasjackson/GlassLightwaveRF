package com.njackson.glass.lightwave.test.client;

import android.test.suitebuilder.annotation.SmallTest;

import com.njackson.glass.lightwave.client.IMessageReceivedCallback;
import com.njackson.glass.lightwave.client.IReceiveUDP;
import com.njackson.glass.lightwave.client.ISendUDP;
import com.njackson.glass.lightwave.client.LightwaveAPI;
import com.njackson.glass.lightwave.client.ResponseParser;

import junit.framework.TestCase;

import org.mockito.ArgumentCaptor;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by server on 05/07/2014.
 */
public class LightWaveAPITest extends TestCase {

    private ISendUDP _sendUDP;
    private IReceiveUDP _receiveUDP;
    private LightwaveAPI _lightWaveAPI;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        _sendUDP = mock(ISendUDP.class);
        _receiveUDP = mock(IReceiveUDP.class);
        _lightWaveAPI = new LightwaveAPI(_sendUDP,_receiveUDP);
        _lightWaveAPI.setTimeout(100);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @SmallTest
    public void testWhenSendingMessageSendSocketIsOpened() throws IOException {
        _lightWaveAPI.sendRawUDP("!R1");
        verify(_sendUDP,times(1)).open(anyString(),anyInt());
    }

    @SmallTest
    public void testWhenSendingMessageReceiveSocketIsOpened() throws IOException {
        _lightWaveAPI.sendRawUDP("!R1");
        verify(_receiveUDP,times(1)).open(anyString(),anyInt(),any(IMessageReceivedCallback.class));
    }

    @SmallTest
    public void testWhenReceivingSocketTimesOutIfNoMessageReceived() throws IOException {
        ResponseParser.Response resp = _lightWaveAPI.sendRawUDP("!R1");
        assertEquals(ResponseParser.Response.TIMEOUT,resp);
    }

    @SmallTest
    public void testWhenReceivingOKMessageOKReturned() throws IOException {
        ArgumentCaptor<IMessageReceivedCallback> captor = ArgumentCaptor.forClass(IMessageReceivedCallback.class);
        _lightWaveAPI.setTimeout(5000);
        new FakeMessageSender().sendMessage("12,OK",_lightWaveAPI,1000);

        ResponseParser.Response resp = _lightWaveAPI.sendRawUDP("!R1"); //blocks
        assertEquals(ResponseParser.Response.OK,resp);
    }

    @SmallTest
    public void testAfterSendingMessageReceiveSocketIsClosed() throws IOException {
        _lightWaveAPI.sendRawUDP("!R1");
        verify(_receiveUDP,times(1)).close();
    }

    @SmallTest
    public void testAfterSendingMessageSendSocketIsClosed() throws IOException {
        _lightWaveAPI.sendRawUDP("!R1");
        verify(_sendUDP,times(1)).close();
    }

    private class FakeMessageSender {
        public void sendMessage(String message, IMessageReceivedCallback callback,int delay) {
            Thread thread = new Thread(new MessageThread(message,callback,delay));
            thread.start();
        }
    }

    private class MessageThread implements Runnable {

        private final String _message;
        private final IMessageReceivedCallback _callback;
        private final int _delay;

        public MessageThread(String message, IMessageReceivedCallback callback,int delay) {
            _message = message;
            _callback = callback;
            _delay = delay;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(_delay);
                _callback.onMessageReceived(_message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
