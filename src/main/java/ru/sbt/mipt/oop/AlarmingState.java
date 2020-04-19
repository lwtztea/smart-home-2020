package ru.sbt.mipt.oop;

public class AlarmingState implements SmartAlarmStates {

    SmartAlarm smartAlarm;

    public AlarmingState(SmartAlarm smartAlarm) { this.smartAlarm = smartAlarm; }

    @Override
    public void activate(int code) {
        // do nothing
    }

    @Override
    public void deactivate(int code) {

        if (smartAlarm.getCode() == code) {
            System.out.println("Smart Alarm is on!");
            smartAlarm.changeState(new DeactivateState(smartAlarm));
        }
    }

    @Override
    public void alarm() {
        // do nothing
    }
}
