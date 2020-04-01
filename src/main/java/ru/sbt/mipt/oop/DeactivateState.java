package ru.sbt.mipt.oop;

public class DeactivateState extends SmartAlarmStates {

    public DeactivateState(SmartAlarm smartAlarm) {
        super(smartAlarm);
    }

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
