package ru.sbt.mipt.oop;

public class DeactivateState implements SmartAlarmStates {

    SmartAlarm smartAlarm;

    public DeactivateState(SmartAlarm smartAlarm) { this.smartAlarm = smartAlarm; }

    @Override
    public void activate(int code) {

        if (code == smartAlarm.getCode()) {
            System.out.println("Smart Alarm is on!");
            smartAlarm.changeState(new ActivateState(smartAlarm));
        } else {
            System.out.println("Incorrect code. Try again");
        }
    }

    @Override
    public void deactivate(int code) {
        // do nothing
    }

    @Override
    public void alarm() {
        // do nothing
    }
}
