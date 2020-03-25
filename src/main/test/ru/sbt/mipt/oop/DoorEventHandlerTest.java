package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class DoorEventHandlerTest {


    @Test
    void handleEvent() throws IOException {

        SmartHome smartHome = new SmartHomeStateReader().readJson("smart-home-1.js");

        String objectId = "1"; // this door is closed before event
        SensorEvent event = new SensorEvent(DOOR_OPEN, objectId);

        DoorEventHandler handler = new DoorEventHandler(smartHome);
        handler.handleSmartHomeEvent(event);

        Iterator iterator = new DoorIterator(smartHome);
        Door changedDoor = (Door) iterator.each(o -> ((Door) o).getId().equals(objectId));
        assertTrue(changedDoor.isOpen());
    }

    @Test
    void alienEvent() throws IOException {

        SmartHome smartHome = new SmartHomeStateReader().readJson("smart-home-1.js");

        String objectId = "1"; // we will change some light state
        SensorEvent event = new SensorEvent(LIGHT_ON, objectId);

        LightEventHandler handler = new LightEventHandler(smartHome);
        handler.handleSmartHomeEvent(event);

        Iterator iterator = new DoorIterator(smartHome);
        Door unchangedDoor = (Door) iterator.each(o -> ((Door) o).getId().equals(objectId));
        assertFalse(unchangedDoor.isOpen());
    }
}
