package ru.sbt.mipt.oop;

public class SmartAlarm {

    private SmartAlarmStates state;
    private int code;

    public SmartAlarm(int code) {
        this.state = new DeactivateState(this);
        this.code = code;
    }

    public void changeState(SmartAlarmStates state) {
        this.state = state;
    }

    public int getCode() {
        return code;
    }

    public void activate(int code){
        state.activate(code);
    }

    public void deactivate(int code){
        state.deactivate(code);
    }

    public void alarm(){
        state.alarm();
    }
}
