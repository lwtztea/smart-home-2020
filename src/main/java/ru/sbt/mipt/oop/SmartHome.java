package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome {
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Object> getHomeObjectList() {

        ArrayList<Object> allObjects = new ArrayList<>();
        for (Room room : this.getRooms()) {
            allObjects.add(room);
            allObjects.addAll(room.getDoors());
            allObjects.addAll(room.getLights());
        }
        return allObjects;
    }
}
