package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LightsIteratorTest {

    @Test
    void testLightIterator() {

        SmartHome smartHome = new TestSmartHomeCreator().getSmartHome();
        Iterator lightIterator = new LightIterator(smartHome);

        assertNull(lightIterator.each(o -> (o instanceof Door)));
        assertNull(lightIterator.each(o -> (o instanceof Room)));
    }
}
