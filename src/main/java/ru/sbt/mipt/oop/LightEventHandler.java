package ru.sbt.mipt.oop;

public class LightEventHandler implements EventHandler {

    private SmartHome smartHome;

    LightEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void handleSmartHomeEvent(SensorEvent event) {

        if (!isLightEvent(event.getType())) return;

        smartHome.execute(object -> {
            if (!(object instanceof Light)) return;
            Light light = (Light) object;
            if (!(light.getId().equals(event.getObjectId()))) return;
            changeLightState(event, light);
        });
    }

    private static boolean isLightEvent(SensorEventType type) {

        return type == SensorEventType.LIGHT_ON || type == SensorEventType.LIGHT_OFF;
    }

    private static void changeLightState(SensorEvent event, Light light) {

        String action = event.getType() == SensorEventType.LIGHT_ON ? "on" : "off";
        light.setOn(event.getType() == SensorEventType.LIGHT_ON);
        System.out.println("Light " + light.getId() + " was turned " + action);
    }
}