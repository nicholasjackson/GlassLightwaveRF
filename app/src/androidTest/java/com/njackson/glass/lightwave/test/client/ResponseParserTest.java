package com.njackson.glass.lightwave.test.client;

import junit.framework.TestCase;

import com.njackson.glass.lightwave.client.ResponseParser;
import com.njackson.glass.lightwave.client.ResponseParser.Response;

/**
 * Created by server on 05/07/2014.
 */
public class ResponseParserTest extends TestCase {

    public void testReturnsOKWhenMessageContainsOK() {
        Response response = ResponseParser.parseResponse("001,OK");
        assertEquals(Response.OK,response);
    }

    public void testReturnsTimeoutWhenMessageContainsTimeout() {
        Response response = ResponseParser.parseResponse("001,TIMEOUT");
        assertEquals(Response.TIMEOUT,response);
    }

}
