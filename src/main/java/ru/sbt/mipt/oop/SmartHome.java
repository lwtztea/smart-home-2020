package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {

    private Collection<Room> rooms;
    private SmartAlarm smartAlarm;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public SmartHome(Collection<Room> rooms, SmartAlarm smartAlarm) {
        this.rooms = rooms;
        this.smartAlarm = smartAlarm;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }
    
    public SmartAlarm getSmartAlarm() {
        return smartAlarm;
    }

    @Override
    public void execute(Action action) {

        rooms.forEach(room -> room.execute(action));
        action.accept(this);
    }
}
