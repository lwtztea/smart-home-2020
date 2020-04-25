package ru.sbt.mipt.oop.serialization;

import ru.sbt.mipt.oop.SensorEventsProcessor;
import ru.sbt.mipt.oop.SmartHomeStateReader;
import ru.sbt.mipt.oop.handlers.*;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        // AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // SensorEventsManager sensorEventsManager = context.getBean(SensorEventsManager.class);
        // sensorEventsManager.start();

        SmartHome smartHome = new SmartHomeStateReader().readJson("smart-home-1.js");

        List<EventHandler> handlers = Arrays.asList(new LightEventHandler(smartHome), new DoorEventHandler(smartHome),
                new HallDoorEventHandler(smartHome), new AlarmEventHandler(smartHome));

        SensorEventsProcessor sensorEventsProcessor = new SensorEventsProcessor(handlers);
        sensorEventsProcessor.run();
    }
}