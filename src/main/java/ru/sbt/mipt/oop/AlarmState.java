package ru.sbt.mipt.oop;

public class AlarmState implements SignalSystem {

    private int code;
    private SmartAlarm smartAlarm;

    public AlarmState(SmartAlarm smartAlarm, int code) {
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
        }
    }

    @Override
    public void alarm() {
        // do nothing
    }
}
