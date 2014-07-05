package com.njackson.glass.lightwave.client;

import java.io.IOException;

public interface ILightwaveAPI {
    public void setTimeout(int milliseconds);
    public ResponseParser.Response forceRegistration() throws IOException;
    public ResponseParser.Response sendRawUDP(String text) throws IOException;
    public ResponseParser.Response sendRoomOff(int Room) throws IOException;
    public ResponseParser.Response sendAllRoomsOff() throws IOException;
    public ResponseParser.Response sendRoomMood(int Room, int Mood) throws IOException;
    public ResponseParser.Response sendDeviceDim(int Room, int Device, int Percent) throws IOException;
    public ResponseParser.Response sendDeviceOnOff(int Room, int Device, int State) throws IOException;
    public ResponseParser.Response sendDeviceLockUnlock(int Room, int Device, int State) throws IOException;
    public ResponseParser.Response sendOpenCloseStop(int Room, int Device, int State) throws IOException;
    public ResponseParser.Response sendHeatOnOff(int Room, int State) throws IOException;

}