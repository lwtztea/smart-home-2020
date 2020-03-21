package ru.sbt.mipt.oop;

public class DeactivateState implements SignalSystem {

    private SmartAlarm smartAlarm;

    public DeactivateState(SmartAlarm smartAlarm) {
        this.smartAlarm = smartAlarm;
    }

    @Override
    public void activate(int code) {

        ActivateState activateState = new ActivateState(smartAlarm, code);
        smartAlarm.setState(activateState);
        activateState.activate(code);
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
