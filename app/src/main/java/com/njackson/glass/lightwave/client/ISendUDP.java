package com.njackson.glass.lightwave.client;

import java.io.Closeable;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by server on 03/07/2014.
 */
public interface ISendUDP extends Closeable {

    public void open(String broadCastAddress, int port) throws SocketException;
    public void sendMessage(String message) throws IOException;

}
