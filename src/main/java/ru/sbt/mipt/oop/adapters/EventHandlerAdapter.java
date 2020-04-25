package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.types.SensorEventType;

import java.util.List;
import java.util.Map;

public class EventHandlerAdapter implements EventHandler {

    private final List<ru.sbt.mipt.oop.handlers.EventHandler> handlers;
    private final Map<String, SensorEventType> adaptedEventType;

    public EventHandlerAdapter(List<ru.sbt.mipt.oop.handlers.EventHandler> handlers, Map<String, SensorEventType> adaptedEventType) {
        this.handlers = handlers;
        this.adaptedEventType = adaptedEventType;
    }

    public void handleEvent(CCSensorEvent event) {
        for (ru.sbt.mipt.oop.handlers.EventHandler handler : handlers) {
            handler.handleEvent(new SensorEvent(adaptedEventType.get(event.getEventType()), event.getObjectId()));
        }
    }
}