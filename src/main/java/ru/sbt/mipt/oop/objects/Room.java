package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;

import java.util.Collection;

public class Room implements Actionable {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public boolean hasLight(String lightId) {

        for (Light currentLight : this.getLights()) {
            if (currentLight.getId().equals(lightId)) return true;
        }
        return false;
    }

    public boolean hasDoor(String doorId) {

        for (Door currentDoor : this.getDoors()) {
            if (currentDoor.getId().equals(doorId)) return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {

        lights.forEach(light -> light.execute(action));
        doors.forEach(door -> door.execute(action));
        action.accept(this);
    }
}
