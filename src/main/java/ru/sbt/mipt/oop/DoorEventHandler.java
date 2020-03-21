package ru.sbt.mipt.oop;

public class DoorEventHandler implements EventHandler {

    private SmartHome smartHome;

    DoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void handleSmartHomeEvent(SensorEvent event) {

        if (!isDoorEvent(event.getType())) return;

        smartHome.execute(object -> {
            if (!(object instanceof Door)) return;
            Door door = (Door) object;
            if (!(door.getId().equals(event.getObjectId()))) return;
            changeDoorState(event, door);
        });
    }

    private static boolean isDoorEvent(SensorEventType type) {

        return type == SensorEventType.DOOR_OPEN || type == SensorEventType.DOOR_CLOSED;
    }

    private static void changeDoorState(SensorEvent event, Door door) {

        String action = event.getType() == SensorEventType.DOOR_OPEN ? "opened" : "closed";
        door.setOpen(event.getType() == SensorEventType.DOOR_OPEN);
        System.out.println("Door " + door.getId() + " was " + action);
    }
}