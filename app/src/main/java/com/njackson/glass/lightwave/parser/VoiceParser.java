package com.njackson.glass.lightwave.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by server on 30/06/2014.
 */
public class VoiceParser {

    public enum Action {
        ON(1),
        OFF(0),
        REGISTER_GLASS(2);

        private final int code;
        private Action(int code) {
            this.code = code;
        }

        public int toInt() {
            return code;
        }

    }

    public enum Room {
        KITCHEN(1),
        LIVING_ROOM(2),
        BED_ROOM(3);

        private final int code;
        private Room(int code) {
            this.code = code;
        }

        public int toInt() {
            return code;
        }
    }

    public enum Device {
        LIGHTS(0);

        private final int code;
        private Device(int code) {
            this.code = code;
        }

        public int toInt() {
            return code;
        }
    }

    private String _command;

    private Action _action;
    public Action get_action() { return _action; }
    public void set_action(Action action) { this._action = action; }

    private Room _room;
    public Room get_room() { return _room; }
    public void set_room(Room room) { this._room = room; }

    private Device _device;
    public Device get_device() { return _device; }
    public void set_device(Device device) { this._device = device; }

    public VoiceParser(String command) throws VoiceParserException {
        _command = command;

        getAction(_command);

        if(_action != Action.REGISTER_GLASS) {
            getRoom(_command);
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

    private void getRoom(String command) throws VoiceParserException {
        Pattern pattern = Pattern.compile("(\\bkitchen|\\bliving room|\\bbed room)");
        Matcher matcher = pattern.matcher(command);

        // check all occurance
        while (matcher.find()) {
            if(matcher.group().equalsIgnoreCase("kitchen")) {
                _room = Room.KITCHEN;
            }else if(matcher.group().equalsIgnoreCase("living room")) {
                _room = Room.LIVING_ROOM;
            } else if(matcher.group().equalsIgnoreCase("bed room")) {
                _room = Room.BED_ROOM;
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
