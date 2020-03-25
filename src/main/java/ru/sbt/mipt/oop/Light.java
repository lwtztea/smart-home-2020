package ru.sbt.mipt.oop;

public class Light implements Actionable{
    private final String id;
    private boolean isOn;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public boolean isOn() {
        return isOn;
    }

    @Override
    public void execute(Action action) {
        action.accept(this);
    }
}
