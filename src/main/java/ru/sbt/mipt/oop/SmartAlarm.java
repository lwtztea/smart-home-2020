package ru.sbt.mipt.oop;

public class SmartAlarm implements SignalSystem {

    private SignalSystem state = new DeactivateState(this);

    @Override
    public void activate(int code) {
        state.activate(code);
    }

    @Override
    public void deactivate(int code) {
        state.deactivate(code);
    }

    @Override
    public void alarm() {
        state.alarm();
    }

    public void setState(SignalSystem state) {
        this.state = state;
    }
}
