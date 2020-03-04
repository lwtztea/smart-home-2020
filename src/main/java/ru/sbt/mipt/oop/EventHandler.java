package ru.sbt.mipt.oop;

public class EventHandler {

    public static void handleSmartHomeEvent(SmartHome smartHome, SensorEvent event) {
        LightEvent.handleLightEvent(smartHome, event);
        DoorEvent.handleDoorEvent(smartHome, event);
    }
}
