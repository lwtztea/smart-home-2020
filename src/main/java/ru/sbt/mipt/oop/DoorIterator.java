package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.function.Function;

public class DoorIterator implements Iterator {

    private ArrayList<Object> cache;

    DoorIterator(SmartHome smartHome) {
        this.cache = smartHome.getHomeObjectList();
    }

    public Door each(Function func) {

        for (Object o : cache) {
            if (o.getClass() == Door.class) {
                boolean isNeededDoor = (boolean) func.apply(o);
                if (isNeededDoor) return (Door) o;
            }
        }
        return null;
    }
}
