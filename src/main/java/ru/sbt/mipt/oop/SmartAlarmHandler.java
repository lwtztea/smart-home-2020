package ru.sbt.mipt.oop;

public class SmartAlarmHandler implements EventHandler{

    private SmartHome smartHome;

    public SmartAlarmHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void handleSmartHomeEvent(SensorEvent event) {

        if (!isAlarmEvent(event.getType())) return;

        if (event.getType() == SensorEventType.ALARM_ACTIVATE) {
            smartHome.getSignalSystem().activate(event.getCode());
        }

        if (event.getType() == SensorEventType.ALARM_DEACTIVATE) {
            smartHome.getSignalSystem().deactivate(event.getCode());
        }
    }

    private static boolean isAlarmEvent(SensorEventType type) {
        return type == SensorEventType.ALARM_ACTIVATE || type == SensorEventType.ALARM_DEACTIVATE;
    }
}
