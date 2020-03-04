package ru.sbt.mipt.oop;

public class DoorEvent {

    public static void handleDoorEvent(SmartHome smartHome, SensorEvent event) {

        if (!isDoorEvent(event.getType())) return;
        Object[] changeableRoomAndDoor = findChangeableRoomAndDoor(smartHome, event.getObjectId);
        if (changeableRoomAndDoor != null) {
            changeDoorState(smartHome, event, (Room) changeableRoomAndDoor[0], (Door) changeableRoomAndDoor[1]);
        }
    }

    private static boolean isDoorEvent(SensorEventType type) {

        if (type == DOOR_OPEN || type == DOOR_CLOSED)
            return true;
        return false;
    }

    private static Object[] findChangeableRoomAndDoor(SmartHome smartHome, String objectId) {

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(objectId)) {
                    return new Object[]{room, door};
                }
            }
        }
        return null;
    }

    private static void changeDoorState(SmartHome smartHome, SensorEvent event, Room room, Door door) {

        if (event.getType() == DOOR_OPEN) {
            door.setOpen(true);
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
        }
        if (event.getType() == DOOR_CLOSED) {
            door.setOpen(false);
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
            if (room.getName().equals("hall")) {
                Light.turnOffAllLights(smartHome);
            }
        }
    }
}
