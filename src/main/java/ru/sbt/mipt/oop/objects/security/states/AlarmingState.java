package ru.sbt.mipt.oop.objects.security.states;

import ru.sbt.mipt.oop.objects.security.SmartAlarm;

public class AlarmingState extends SmartAlarmStates {

    public AlarmingState(SmartAlarm smartAlarm) {
        super(smartAlarm);
    }

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