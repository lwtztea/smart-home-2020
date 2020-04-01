package ru.sbt.mipt.oop;

public class AlarmEventHandler implements EventHandler {

    private SmartHome smartHome;

    AlarmEventHandler(SmartHome smartHome) {
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
