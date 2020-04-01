package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.types.SensorEventType;

public class AlarmEventHandler implements EventHandler {

    private SmartHome smartHome;

    public AlarmEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void handleEvent(SensorEvent event) {

        if (event.getType() == SensorEventType.ALARM_ACTIVATE) {
            smartHome.getSmartAlarm().activate(event.getCode());
        }
        if (event.getType() == SensorEventType.ALARM_DEACTIVATE) {
            smartHome.getSmartAlarm().deactivate(event.getCode());
        }
    }
}