package com.njackson.glass.lightwave.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;

/*
 * A server object that runs in own thread.
 * Waits for text UDP commands and then adds them to a buffer (FIFO)
 * Periodically removes items from buffer and sends UDP commands to port 9760 on LWRF box
 * Delay between UDP transmission prevents flooding of port 9760 on LWRF box
 */
public class SendUDP implements ISendUDP {

    private String _broadcastaddress;
    private int _port;
    private DatagramSocket _transmitSocket;
    private String _message;

    @Override
    public void open(String broadcastaddress, int port) throws SocketException {
        _broadcastaddress = broadcastaddress;
        _port = port;
        _transmitSocket = new DatagramSocket();
    }

    @Override
    public void sendMessage(String message) throws IOException {
        _message = message;
        SocketThread socketThread = new SocketThread();
        Thread thread = new Thread(socketThread);
        thread.start();
    }

    @Override
    public void close() throws IOException {
        if(_transmitSocket != null) {
            _transmitSocket.close();
        }
    }

    private class SocketThread implements Runnable {

        @Override
        public void run() {
            byte[] sendData = new byte[1024];
            sendData = _message.getBytes();
            InetAddress IPAddress = null;
            try {
                IPAddress = InetAddress.getByName(_broadcastaddress);
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, _port); //Send broadcast UDP to 9760 port
                _transmitSocket.send(sendPacket);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

