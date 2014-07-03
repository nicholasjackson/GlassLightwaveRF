package com.njackson.glass.lightwave.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/*
 * Accepts: polling interval between polls, start polling, stop polling, length of time to poll until
 * 
 */

public class ReceiveUDP implements IReceiveUDP {

    private DatagramSocket _receiveSocket;
    private SocketThread _socketThread;
    private IMessageReceivedCallback _callback;

    @Override
    public void open(String broadcastaddress, int port,IMessageReceivedCallback callback) throws IOException {
        _callback = callback;
        _receiveSocket = new DatagramSocket(port);
        _socketThread = new SocketThread();
        Thread thread = new Thread(_socketThread);
        thread.start();
    }

    @Override
    public void close() throws IOException {
        if(_socketThread != null) {
            _socketThread.cancel();
        }

        if(_receiveSocket != null && !_receiveSocket.isClosed()) {
            _receiveSocket.close();
        }
    }

    private class SocketThread implements Runnable {

        private boolean _running = true;

        @Override
        public void run() {
            while(_running) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                try {
                    _receiveSocket.receive(receivePacket); // blocks until data is received or a timeout occurs
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                _callback.onMessageReceived(receivedMessage);
            }
        }

        public void cancel() {
            _running = false;
        }

    }
}

