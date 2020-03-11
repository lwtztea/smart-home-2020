package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.function.Function;

public class LightIterator implements Iterator {

    private ArrayList<Object> cache;

    LightIterator(SmartHome smartHome) {
        this.cache = smartHome.getHomeObjectList();
    }

    public Light each(Function func) {

        for (Object o : cache) {
            if (o.getClass() == Light.class) {
                boolean isNeededLight = (boolean) func.apply(o);
                if (isNeededLight) return (Light) o;
            }
        }
        return null;
    }
}
