package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.types.SensorEventType;

public class SensorEvent {
    private SensorEventType type;
    private String objectId;
    private int code = 0;

    public SensorEvent(SensorEventType type, String objectId) {
        this.type = type;
        this.objectId = objectId;
    }

    public SensorEvent(SensorEventType type, String objectId, int code) {
        this.type = type;
        this.objectId = objectId;
        this.code = code;
    }

    public SensorEventType getType() {
        return type;
    }

    public String getObjectId() {
        return objectId;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}