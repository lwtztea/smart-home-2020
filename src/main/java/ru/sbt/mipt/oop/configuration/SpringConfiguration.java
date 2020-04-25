package ru.sbt.mipt.oop.configuration;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.SmartHomeStateReader;
import ru.sbt.mipt.oop.adapters.EventHandlerAdapter;
import ru.sbt.mipt.oop.handlers.*;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.SmartRemoteControl;
import ru.sbt.mipt.oop.remotecontrol.commands.*;
import ru.sbt.mipt.oop.types.SensorEventType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SpringConfiguration {

    @Bean
    SmartHome smartHome() {
        return new SmartHomeStateReader().readJson("smart-home-1.js");
    }

    @Bean
    EventHandler alarmEventHandler(SmartHome smartHome) {
        return new AlarmEventHandler(smartHome);
    }

    @Bean
    EventHandler lightEventHandler(SmartHome smartHome) {
        return new LightEventHandler(smartHome);
    }

    @Bean
    EventHandler doorEventHandler(SmartHome smartHome) {
        return new DoorEventHandler(smartHome);
    }

    @Bean
    EventHandler hallDoorEventHandler(SmartHome smartHome) {
        return new HallDoorEventHandler(smartHome);
    }

    @Bean
    Map<String, SensorEventType> adaptedEventType() {
        return new HashMap<String, SensorEventType>() {
            {
                put("LightIsOn", SensorEventType.LIGHT_ON);
                put("LightIsOff", SensorEventType.LIGHT_OFF);
                put("DoorIsOpen", SensorEventType.DOOR_OPEN);
                put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
            }
        };
    }

    @Bean
    public SensorEventsManager sensorEventsManager(List<EventHandler> handlers, Map<String, SensorEventType> adaptedEventType) {

        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(new EventHandlerAdapter(handlers, adaptedEventType));
        return sensorEventsManager;
    }

    @Bean
    int alarmCode() {
        return 1234;
    }

    @Bean
    Command activateAlarm(SmartHome smartHome, int alarmCode) {
        return new ActivateAlarm(smartHome, alarmCode);
    }

    @Bean
    Command closeHallDoor(SmartHome smartHome) {
        return new CloseHallDoor(smartHome);
    }

    @Bean
    Command turnOffAllLights(SmartHome smartHome) {
        return new TurnOffAllLights(smartHome);
    }

    @Bean
    Command turnOnAlarmingMode(SmartHome smartHome) {
        return new TurnOnAlarmingMode(smartHome);
    }

    @Bean
    Command turnOnAllLights(SmartHome smartHome) {
        return new TurnOnAllLights(smartHome);
    }

    @Bean
    Command turnOnHallLight(SmartHome smartHome) {
        return new TurnOnHallLight(smartHome);
    }

    @Bean
    public RemoteControl remoteControl(Command activateAlarm, Command turnOnAlarmingMode,
                                       Command closeHallDoor, Command turnOnHallLight,
                                       Command turnOnAllLights, Command turnOffAllLights) {
        SmartRemoteControl remoteControl = new SmartRemoteControl();
        remoteControl.setCommand("A", activateAlarm);
        remoteControl.setCommand("B", turnOnAlarmingMode);
        remoteControl.setCommand("C", closeHallDoor);
        remoteControl.setCommand("D", turnOnHallLight);
        remoteControl.setCommand("1", turnOnAllLights);
        remoteControl.setCommand("2", turnOffAllLights);
        return remoteControl;
    }

    @Bean
    String rcId() {
        return "newRemoteControl";
    }

    @Bean
    public RemoteControlRegistry remoteControlRegistry(RemoteControl remoteControl, String rcId) {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(remoteControl, rcId);
        return remoteControlRegistry;
    }
}
