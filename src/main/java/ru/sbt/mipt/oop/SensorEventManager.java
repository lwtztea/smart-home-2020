package ru.sbt.mipt.oop;

import java.util.List;

public class SensorEventsManager {

    private List<EventHandler> handlers;

    public SensorEventsManager(List<EventHandler> handlers) {
        this.handlers = handlers;
    }

    public void start() {

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
