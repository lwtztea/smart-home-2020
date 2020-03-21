package ru.sbt.mipt.oop;

public class HallDoorEventHandler implements EventHandler {

    private SmartHome smartHome;

    HallDoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void handleSmartHomeEvent(SensorEvent event) {

        if (event.getType() != SensorEventType.DOOR_CLOSED) return;

        Room changedRoom = findRoomWhereChangedDoor(smartHome, event.getObjectId());
        if (changedRoom == null) return;

        if (changedRoom.getName().equals("hall")) {
            // дверь закрывается в обработчике события двери
            turnOffAllLights(smartHome);
        }
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

    public static void turnOffAllLights(SmartHome smartHome) {

        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                CommandSender.sendCommand(command);
            }
        }
    }
}