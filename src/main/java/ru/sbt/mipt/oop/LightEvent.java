package ru.sbt.mipt.oop;

public class LightEvent {

    public static void handleLightEvent(SmartHome smartHome, SensorEvent event) {

        if (!isLightEvent(event.getType())) return;
        Object[] changeableRoomAndLight = findChangeableRoomAndLight(smartHome, event.getObjectId);
        if (changeableRoomAndLight != null) {
            changeLightState(smartHome, event, (Room) changeableRoomAndLight[0], (Light) changeableRoomAndLight[1]);
        }
    }

    private static boolean isLightEvent(SensorEventType type) {

        if (type == LIGHT_ON || type == LIGHT_OFF)
            return true;
        return false;
    }

    private static Object[] findChangeableRoomAndLight(SmartHome smartHome, String objectId) {

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(objectId) {
                    return new Object[]{room, light};
                }
            }
        }
        return null;
    }

    private static void changeLightState(SmartHome smartHome, SensorEvent event, Room room, Light light) {

        if (event.getType() == LIGHT_ON) {
            light.setOn(true);
            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
        }
        if (event.getType() == LIGHT_OFF) {
            light.setOn(false);
            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
        }
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
