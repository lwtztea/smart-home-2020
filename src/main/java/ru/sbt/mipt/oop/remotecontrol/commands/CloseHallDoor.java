package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.objects.*;

public class CloseHallDoor implements Command {

    private final SmartHome smartHome;

    CloseHallDoor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (!(object instanceof Room)) return;
            Room room = (Room) object;
            if (!(room.getName().equals("hall"))) return;
            room.execute(o -> {
                if (!(o instanceof Door)) return;
                Door door = (Door) o;
                door.setOpen(false);
            });
        });
    }
}