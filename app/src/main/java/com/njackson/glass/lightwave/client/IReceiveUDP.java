package com.njackson.glass.lightwave.client;

import java.io.Closeable;
import java.io.IOException;
import java.net.SocketException;

/**
 * Created by server on 03/07/2014.
 */
public interface IReceiveUDP extends Closeable {

    public void open(String broadcastaddress,int port, IMessageReceivedCallback callback) throws IOException;

}