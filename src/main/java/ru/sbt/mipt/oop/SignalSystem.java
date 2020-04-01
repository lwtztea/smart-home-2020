package ru.sbt.mipt.oop;

public abstract class SmartAlarmStates {

    SmartAlarm smartAlarm;

    SmartAlarmStates(SmartAlarm smartAlarm) {
        this.smartAlarm = smartAlarm;
    }

    public abstract void activate(int code);
    public abstract void deactivate(int code);
    public abstract void alarm();
}
