package ru.sbt.mipt.oop;

public class HallDoorEventHandler implements EventHandler {

    private SmartHome smartHome;

    HallDoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void handleSmartHomeEvent(SensorEvent event) {

        if (event.getType() != SensorEventType.DOOR_CLOSED) return;

        smartHome.execute(object -> {
            if (!(object instanceof Room)) return;
            Room room = (Room) object;
            if (!(room.getName().equals("hall"))) return;
            room.execute(roomObjects -> {
                if (!(roomObjects instanceof Door)) return;
                Door door = (Door) roomObjects;
                if (!(door.getId().equals(event.getObjectId()))) return;
                turnOffAllLights(smartHome);
            });
        });
    }

    public static void turnOffAllLights(SmartHome smartHome) {

        smartHome.execute(object -> {
            if (!(object instanceof Light)) return;
            Light light = (Light) object;
            light.setOn(false);
            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
            CommandSender.sendCommand(command);
        });
    }
}