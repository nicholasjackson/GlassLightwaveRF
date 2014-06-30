package com.njackson.glass.lightwave.client;

public interface ILightwaveAPI {

    public void forceRegistration();
    public void sendRawUDP(String text);
    public void sendRoomOff(int Room);
    public void sendAllRoomsOff();
    public void sendRoomMood(int Room, int Mood);
    public void sendDeviceDim(int Room, int Device, int Percent);
    public void sendDeviceOnOff(int Room, int Device, int State);
    public void sendDeviceLockUnlock(int Room, int Device, int State);
    public void sendOpenCloseStop(int Room, int Device, int State);
    public void sendHeatOnOff(int Room, int State);

}