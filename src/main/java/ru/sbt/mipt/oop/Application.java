package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args) throws IOException {

        SmartHome smartHome = new SmartHomeStateReader().readJson("smart-home-1.js");
        SensorEvent event = new SensorEventGenerator().getRandomSensorEvent();
        List<EventHandler> handlers = Arrays.asList(new DoorEventHandler(smartHome), new LightEventHandler(smartHome));
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventHandler handler: handlers) {
                handler.handleSmartHomeEvent(event);
            }
            event = new SensorEventGenerator().getRandomSensorEvent();
        }
    }
}
