package ru.sbt.mipt.oop;

public class Application {

    public static void main(String... args) {

        SmartHome smartHome = SmartHomeStateReader.readJson("smart-home-1.js");
        SensorEvent event = SensorEvent.getNextRandomSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            EventHandler.handleSmartHomeEvent(smartHome, event);
            event = SensorEvent.getNextRandomSensorEvent();
        }
    }
}
