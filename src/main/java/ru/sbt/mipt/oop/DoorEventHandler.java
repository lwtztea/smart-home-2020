package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class DoorEventHandler implements EventHandler {

    private SmartHome smartHome;

    DoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void handleSmartHomeEvent(SensorEvent event) {

        if (!isDoorEvent(event.getType())) return;

        Room changedRoom = findRoomWhereChangedDoor(smartHome, event.getObjectId());
        if (changedRoom == null) return;
        
        if (changedRoom.getName().equals("hall") && event.getType() == DOOR_CLOSED) {
            hallRoomEvent(smartHome, changedRoom, event);
            return;
        }

        Door changedDoor = findChangedDoorInRoom(changedRoom, event.getObjectId());
        if (changedDoor == null) return;

        changeDoorState(event, changedRoom, changedDoor);
    }

    private static boolean isDoorEvent(SensorEventType type) {

        return type == DOOR_OPEN || type == DOOR_CLOSED;
    }

    private static Room findRoomWhereChangedDoor(SmartHome smartHome, String objectId) {

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(objectId)) {
                    return room;
                }
            }
        }
        return null;
    }

    private static Door findChangedDoorInRoom(Room room, String objectId) {

        for (Door door : room.getDoors()) {
            if (door.getId().equals(objectId)) {
                return door;
            }
        }
        return null;
    }

    private static void changeDoorState(SensorEvent event, Room room, Door door) {

        String action = event.getType() == DOOR_OPEN ? "opened" : "closed";
        door.setOpen(event.getType() == DOOR_OPEN);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was " + action);
    }

    private static void hallRoomEvent(SmartHome smartHome, Room hall, SensorEvent event) {

        Door changedDoor = findChangedDoorInRoom(hall, event.getObjectId());
        assert changedDoor != null;
        changedDoor.setOpen(false);
        System.out.println("Door " + changedDoor.getId() + " in hall was closed.");
        LightEventHandler.turnOffAllLights(smartHome);
    }
}
