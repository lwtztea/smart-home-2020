package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.remotecontrol.commands.Command;

import java.util.HashMap;
import java.util.Map;

public class RemoteControl implements rc.RemoteControl {

    private Map<String, Command> remoteControlPanel = new HashMap<>();

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (remoteControlPanel.containsKey(buttonCode)) {
            remoteControlPanel.get(buttonCode).execute();
        }
    }

    public void setCommand(String buttonCode, Command command) {
        if (remoteControlPanel.containsKey(buttonCode)) {
            remoteControlPanel.replace(buttonCode, command);
        } else {
            remoteControlPanel.put(buttonCode, command);
        }
    }
}