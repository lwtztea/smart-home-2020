package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.SensorEvent;

public interface EventHandler {

    void handleEvent(SensorEvent event);
}