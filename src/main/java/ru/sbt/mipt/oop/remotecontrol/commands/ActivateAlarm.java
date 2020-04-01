package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.objects.SmartHome;

public class ActivateAlarm implements Command {

    private final SmartHome smartHome;
    private final int code;

    ActivateAlarm(SmartHome smartHome, int code) {
        this.smartHome = smartHome;
        this.code = code;
    }

    @Override
    public void execute() {
        smartHome.getSmartAlarm().activate(code);
    }
}
