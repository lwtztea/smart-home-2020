package ru.sbt.mipt.oop.decorators;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.handlers.EventHandler;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.objects.security.SmartAlarm;
import ru.sbt.mipt.oop.objects.security.states.*;

import java.util.List;

import static ru.sbt.mipt.oop.types.SensorEventType.ALARM_DEACTIVATE;

public class EventHandlerDecorator implements EventHandler {

    private SmartAlarm smartAlarm;
    private final List<EventHandler> eventHandlers;

    EventHandlerDecorator(SmartHome smartHome, List<EventHandler> eventHandlers) {
        this.smartAlarm = smartHome.getSmartAlarm();
        this.eventHandlers = eventHandlers;
    }

    @Override
    public void handleEvent(SensorEvent event) {

        if (smartAlarm.getState() instanceof ActivateState && event.getType() != ALARM_DEACTIVATE) {
            smartAlarm.alarm();
        }

        if (smartAlarm.getState() instanceof AlarmingState && event.getType() != ALARM_DEACTIVATE) {
            System.out.println("Sending sms");
            return;
        }

        for (EventHandler eventHandler : eventHandlers) {
            eventHandler.handleEvent(event);
        }
    }
}
