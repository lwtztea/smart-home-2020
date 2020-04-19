package ru.sbt.mipt.oop;

public class ActivateState implements SmartAlarmStates {

    SmartAlarm smartAlarm;

    public ActivateState(SmartAlarm smartAlarm) { this.smartAlarm = smartAlarm; }

    @Override
    public void activate(int code) {
        // do nothing
    }

    @Override
    public void deactivate(int code) {

        if (smartAlarm.getCode() == code) {
            System.out.println("Smart Alarm is off!");
            smartAlarm.changeState(new DeactivateState(smartAlarm));
        } else {
            System.out.println("ALARM!");
            smartAlarm.changeState(new AlarmingState(smartAlarm));
        }
    }

    @Override
    public void alarm() {

        System.out.println("ALARM!");
        smartAlarm.changeState(new AlarmingState(smartAlarm));
    }
}
