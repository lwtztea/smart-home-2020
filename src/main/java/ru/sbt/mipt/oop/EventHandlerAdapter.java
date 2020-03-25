package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;

public class EventHandlerAdapter implements EventHandler {

    private final ru.sbt.mipt.oop.EventHandler eventHandler;

    public EventHandlerAdapter(ru.sbt.mipt.oop.EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public void handleEvent(CCSensorEvent event) {
        eventHandler.handleEvent(new SensorEventAdapter(event));
    }
}