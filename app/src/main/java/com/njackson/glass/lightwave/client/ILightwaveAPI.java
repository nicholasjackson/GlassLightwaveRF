package com.njackson.glass.lightwave.client;

import java.io.IOException;

public interface ILightwaveAPI {

    public void forceRegistration() throws IOException;
    public void sendRawUDP(String text) throws IOException;
    public void sendRoomOff(int Room) throws IOException;
    public void sendAllRoomsOff() throws IOException;
    public void sendRoomMood(int Room, int Mood) throws IOException;
    public void sendDeviceDim(int Room, int Device, int Percent) throws IOException;
    public void sendDeviceOnOff(int Room, int Device, int State) throws IOException;
    public void sendDeviceLockUnlock(int Room, int Device, int State) throws IOException;
    public void sendOpenCloseStop(int Room, int Device, int State) throws IOException;
    public void sendHeatOnOff(int Room, int State) throws IOException;

}