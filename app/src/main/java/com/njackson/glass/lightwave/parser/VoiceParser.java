package com.njackson.glass.lightwave.parser;

import com.njackson.glass.lightwave.entities.Config;
import com.njackson.glass.lightwave.entities.Room;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by server on 30/06/2014.
 */
public class VoiceParser {

    public enum Action {
        ON(1,"On"),
        OFF(0,"Off"),
        REGISTER_GLASS(2,"Register Glass");

        private final int _code;
        private final String _name;
        private Action(int code,String name) {
            this._code = code;
            this._name = name;
        }

        public int toInt() {
            return _code;
        }
        public String toString() { return _name; }

    }

    public enum Device {
        LIGHTS(0,"Lights");

        private final int _code;
        private final String _name;
        private Device(int code,String name) {
            this._code = code;
            this._name = name;
        }

        public int toInt() {
            return _code;
        }
        public String toString() {return _name;}
    }

    private String _command;

    private Action _action;
    public Action get_action() { return _action; }

    private Room _room;
    public Room get_room() { return _room; }

    private Device _device;
    public Device get_device() { return _device; }

    public VoiceParser(String command,Config config) throws VoiceParserException {
        _command = command;

        getAction(_command);

        if(_action != Action.REGISTER_GLASS) {
            getRoom(_command,config);
            getDevice(_command);
        }
    }

    private void getAction(String command) throws VoiceParserException {
        Pattern pattern = Pattern.compile("(\\bon|\\boff|\\bregister glass)");
        Matcher matcher = pattern.matcher(command);

        while (matcher.find()) {
            if(matcher.group().equalsIgnoreCase("on"))
              _action = Action.ON;
            else if(matcher.group().equalsIgnoreCase("off"))
                _action = Action.OFF;
            else if(matcher.group().equalsIgnoreCase("register glass"))
                _action = Action.REGISTER_GLASS;
        }

        if(_action == null) {
            throw new VoiceParserException();
        }
    }

    private void getRoom(String command,Config config) throws VoiceParserException {
        for(int n=0;n < config.rooms.size();n++) {
            if(command.toLowerCase().contains(config.rooms.get(n).name.toLowerCase())) {
                _room = config.rooms.get(n);
            }
        }

        if(_room == null) {
            throw new VoiceParserException();
        }
    }

    private void getDevice(String command) throws VoiceParserException {
        Pattern pattern = Pattern.compile("(\\blight|\\blights)");
        Matcher matcher = pattern.matcher(command);

        // check all occurance
        while (matcher.find()) {
            _device = Device.LIGHTS;
        }

        if(_device == null) {
            throw new VoiceParserException();
        }
    }

}
