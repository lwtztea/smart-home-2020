package ru.sbt.mipt.oop;

import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args) {

        SmartHome smartHome = new SmartHomeStateReader().readJson("smart-home-1.js");

        List<EventHandler> handlers = Arrays.asList(new LightEventHandler(smartHome), new DoorEventHandler(smartHome),
                new HallDoorEventHandler(smartHome));

        EventHandlerDecorator eventHandlerDecorator = new EventHandlerDecorator(smartHome, handlers);
        SensorEventsProcessor sensorEventsProcessor = new SensorEventsProcessor(Collections.singletonList(eventHandlerDecorator));
        sensorEventsManager.start();
    }
}
