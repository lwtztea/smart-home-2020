package ru.sbt.mipt.oop;

import java.util.List;

import static ru.sbt.mipt.oop.SensorEventType.ALARM_DEACTIVATE;

public class EventHandlerDecorator implements EventHandler {

    private SmartAlarm smartAlarm;
    private final List<EventHandler> eventHandlers;

    EventHandlerDecorator(SmartHome smartHome, List<EventHandler> eventHandlers) {
        this.smartAlarm = smartHome.getSmartAlarm();
        this.eventHandlers = eventHandlers;
    }

    @Override
    public void handleEvent(SensorEvent event) {

        if (smartAlarm.getState() instanceof ActivateState) {
            smartAlarm.alarm();
        }

        if (smartAlarm.getState() instanceof AlarmingState) {
            System.out.println("Sending sms");
            return;
        }

        for (EventHandler eventHandler : eventHandlers) {
            eventHandler.handleEvent(event);
        }
    }
}
