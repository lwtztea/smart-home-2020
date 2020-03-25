package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoorIteratorTest {

    @Test
    void testLightIterator() {

        SmartHome smartHome = new TestSmartHomeCreator().getSmartHome();
        Iterator doorIterator = new DoorIterator(smartHome);

        assertNull(doorIterator.each(o -> (o instanceof Light)));
        assertNull(doorIterator.each(o -> (o instanceof Room)));
    }
}
