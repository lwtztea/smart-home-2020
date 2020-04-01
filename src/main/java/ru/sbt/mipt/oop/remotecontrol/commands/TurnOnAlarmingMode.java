package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.objects.SmartHome;

public class TurnOnAlarmingMode implements Command {

    private final SmartHome smartHome;

    TurnOnAlarmingMode(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.getSmartAlarm().alarm();
    }
}
