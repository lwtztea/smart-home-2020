package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomIteratorTest {

    @Test
    void testLightIterator() {

        SmartHome smartHome = new TestSmartHomeCreator().getSmartHome();
        Iterator roomIterator = new RoomIterator(smartHome);

        assertNull(roomIterator.each(o -> (o instanceof Light)));
        assertNull(roomIterator.each(o -> (o instanceof Door)));
    }
}
