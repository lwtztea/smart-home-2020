package ru.sbt.mipt.oop;

public class ActivateState implements SignalSystem {

    private int code;
    private SmartAlarm smartAlarm;

    public ActivateState(SmartAlarm smartAlarm, int code) {
        this.smartAlarm = smartAlarm;
        this.code = code;
    }

    @Override
    public void activate(int code) {
        // do nothing
    }

    @Override
    public void deactivate(int code) {

        if (this.code == code) {
            DeactivateState deactivateState = new DeactivateState(smartAlarm);
            smartAlarm.setState(deactivateState);
            deactivateState.deactivate(code);
        } else {
            System.out.println("ALARM!");
            AlarmState alarmState = new AlarmState(smartAlarm, code);
            smartAlarm.setState(alarmState);
            alarmState.alarm();
        }
    }

    @Override
    public void alarm() {

        System.out.println("ALARM!");
        AlarmState alarmState = new AlarmState(smartAlarm, code);
        smartAlarm.setState(alarmState);
        alarmState.alarm();
    }
}
