package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.handlers.EventHandler;
import ru.sbt.mipt.oop.types.SensorEventType;

import java.util.List;

public class SensorEventsProcessor {

    private List<EventHandler> handlers;

    public SensorEventsProcessor(List<EventHandler> handlers) {
        this.handlers = handlers;
    }

    public void run() {

        SensorEvent event = getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventHandler handler : handlers) {
                handler.handleEvent(event);
            }
            event = getNextSensorEvent();
        }
    }

    private SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}