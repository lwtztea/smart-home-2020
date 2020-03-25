package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;

import java.util.HashMap;
import java.util.Map;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class SensorEventAdapter extends SensorEvent {

    private static final Map<String, SensorEventType> adaptedEventType = new HashMap<String, SensorEventType>() {
        {
            put("LightIsOn", LIGHT_ON);
            put("LightIsOff", SensorEventType.LIGHT_OFF);
            put("DoorIsOpen", SensorEventType.DOOR_OPEN);
            put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        }
    };

    public SensorEventAdapter(CCSensorEvent event) {
        super(adaptedEventType.get(event.getEventType()), event.getObjectId());
    }
}