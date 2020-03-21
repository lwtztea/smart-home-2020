package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class LightEventHandler implements EventHandler {

    private SmartHome smartHome;

    LightEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void handleSmartHomeEvent(SensorEvent event) {

        if (!isLightEvent(event.getType())) return;

        Room changedRoom = findRoomWhereChangedLight(smartHome, event.getObjectId());
        if (changedRoom == null) return;

        Light changedLight = findChangedLightInRoom(changedRoom, event.getObjectId());
        if (changedLight == null) return;

        changeLightState(event, changedRoom, changedLight);
    }

    private static boolean isLightEvent(SensorEventType type) {

        return type == LIGHT_ON || type == LIGHT_OFF;
    }

    private static Room findRoomWhereChangedLight(SmartHome smartHome, String objectId) {

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(objectId)) {
                    return room;
                }
            }
        }
        return null;
    }

    private static Light findChangedLightInRoom(Room room, String objectId) {

        for (Light light : room.getLights()) {
            if (light.getId().equals(objectId)) {
                return light;
            }
        }
        return null;
    }

    private static void changeLightState(SensorEvent event, Room room, Light light) {

        String action = event.getType() == LIGHT_ON ? "on" : "off";
        light.setOn(event.getType() == LIGHT_ON);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned " + action);
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
