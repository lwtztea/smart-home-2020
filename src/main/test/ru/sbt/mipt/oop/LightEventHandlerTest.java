package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class LightEventHandlerTest {

    @Test
    void handleEvent() throws IOException {

        SmartHome smartHome = new SmartHomeStateReader().readJson("smart-home-1.js");

        String objectId = "2"; // this light is on before event
        SensorEvent event = new SensorEvent(LIGHT_OFF, objectId);

        LightEventHandler handler = new LightEventHandler(smartHome);
        handler.handleSmartHomeEvent(event);

        Iterator iterator = new LightIterator(smartHome);
        Light changedLight = (Light) iterator.each(o -> ((Light) o).getId().equals(objectId));
        assertFalse(changedLight.isOn());
    }

    @Test
    void alienEvent() throws IOException {

        SmartHome smartHome = new SmartHomeStateReader().readJson("smart-home-1.js");

        String objectId = "2"; // we will change some door state
        SensorEvent event = new SensorEvent(DOOR_OPEN, objectId);

        DoorEventHandler handler = new DoorEventHandler(smartHome);
        handler.handleSmartHomeEvent(event);

        Iterator iterator = new LightIterator(smartHome);
        Light unchangedLight = (Light) iterator.each(o -> ((Light) o).getId().equals(objectId));
        assertTrue(unchangedLight.isOn());
    }
}
