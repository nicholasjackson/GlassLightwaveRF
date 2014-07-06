package com.njackson.glass.lightwave.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * Created by server on 05/07/2014.
 */
public class Config {
    public ArrayList<Room> rooms;

    public static Config parseJSON(String json) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(json,Config.class);
    }
}
