package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.function.Function;

public class RoomIterator implements Iterator {

    private ArrayList<Object> cache;

    RoomIterator (SmartHome smartHome) {
        this.cache = smartHome.getHomeObjectList();
    }

    public Room each(Function func) {

        for (Object o : cache) {
            if (o.getClass() == Room.class) {
                boolean isNeededRoom = (boolean) func.apply(o);
                if (isNeededRoom) return (Room) o;
            }
        }
        return null;
    }
}
