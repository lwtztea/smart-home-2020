package ru.sbt.mipt.oop;

public interface SmartAlarmStates {

    public abstract void activate(int code);
    public abstract void deactivate(int code);
    public abstract void alarm();
}
