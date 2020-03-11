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

        Light changedLight = findChangedLight(smartHome, event.getObjectId());
        if (changedLight == null) return;

        changeLightState(event, changedRoom, changedLight);
    }

    private static boolean isLightEvent(SensorEventType type) {

        return type == LIGHT_ON || type == LIGHT_OFF;
    }

    private static Room findRoomWhereChangedLight(SmartHome smartHome, String objectId) {

        Iterator iterator = new RoomIterator(smartHome);
        return (Room) iterator.each(o -> ((Room) o).hasLight(objectId));
    }

    private static Light findChangedLight(SmartHome smartHome, String objectId) {

        Iterator iterator = new LightIterator(smartHome);
        return (Light) iterator.each(o -> ((Light) o).getId().equals(objectId));
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
