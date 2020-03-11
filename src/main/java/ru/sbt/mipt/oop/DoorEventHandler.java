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

        Door changedDoor = findChangedDoor(smartHome, event.getObjectId());
        if (changedDoor == null) return;

        if (changedRoom.getName().equals("hall") && event.getType() == DOOR_CLOSED) {
            hallRoomEvent(smartHome, changedDoor);
            return;
        }

        changeDoorState(event, changedRoom, changedDoor);
    }

    private static boolean isDoorEvent(SensorEventType type) {

        return type == DOOR_OPEN || type == DOOR_CLOSED;
    }

    private static Room findRoomWhereChangedDoor(SmartHome smartHome, String objectId) {

        Iterator iterator = new RoomIterator(smartHome);
        return (Room) iterator.each(o -> (((Room) o).hasDoor(objectId)));
    }

    private static Door findChangedDoor(SmartHome smartHome, String objectId) {

        Iterator iterator = new DoorIterator(smartHome);
        return (Door) iterator.each(o -> ((Door) o).getId().equals(objectId));
    }

    private static void changeDoorState(SensorEvent event, Room room, Door door) {

        String action = event.getType() == DOOR_OPEN ? "opened" : "closed";
        door.setOpen(event.getType() == DOOR_OPEN);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was " + action);
    }

    private static void hallRoomEvent(SmartHome smartHome, Door changedDoor) {

        changedDoor.setOpen(false);
        System.out.println("Door " + changedDoor.getId() + " in hall was closed.");
        LightEventHandler.turnOffAllLights(smartHome);
    }
}
