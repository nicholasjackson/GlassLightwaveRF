package com.njackson.glass.lightwave.client;

import java.io.IOException;

public interface ILightwaveAPI {

    public LightwaveAPI.Response forceRegistration() throws IOException;
    public LightwaveAPI.Response sendRawUDP(String text) throws IOException;
    public LightwaveAPI.Response sendRoomOff(int Room) throws IOException;
    public LightwaveAPI.Response sendAllRoomsOff() throws IOException;
    public LightwaveAPI.Response sendRoomMood(int Room, int Mood) throws IOException;
    public LightwaveAPI.Response sendDeviceDim(int Room, int Device, int Percent) throws IOException;
    public LightwaveAPI.Response sendDeviceOnOff(int Room, int Device, int State) throws IOException;
    public LightwaveAPI.Response sendDeviceLockUnlock(int Room, int Device, int State) throws IOException;
    public LightwaveAPI.Response sendOpenCloseStop(int Room, int Device, int State) throws IOException;
    public LightwaveAPI.Response sendHeatOnOff(int Room, int State) throws IOException;

}