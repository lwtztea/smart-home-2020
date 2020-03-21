package ru.sbt.mipt.oop;

import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args) {

        SmartHome smartHome = new SmartHomeStateReader().readJson("smart-home-1.js");

        List<EventHandler> handlers = Arrays.asList(new LightEventHandler(smartHome), new DoorEventHandler(smartHome),
                new HallDoorEventHandler(smartHome));

        SensorEventGenerator eventGenerator = new RandomEventGenerator();
        SensorEvent event = eventGenerator.getEvent();

        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventHandler handler : handlers) {
                handler.handleSmartHomeEvent(event);
            }
            event = eventGenerator.getEvent();
        }
    }
}
