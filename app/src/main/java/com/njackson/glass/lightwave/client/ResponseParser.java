package com.njackson.glass.lightwave.client;

/**
 * Created by server on 05/07/2014.
 */
public class ResponseParser {

    public enum Response{
        OK,
        ERROR,
        TIMEOUT
    }

    public static Response parseResponse(String message) {

        if(message.contains("TIMEOUT")) {
            return Response.TIMEOUT;
        } else if(message.contains("OK")) {
            return Response.OK;
        } else {
            return Response.ERROR;
        }

    }

}
