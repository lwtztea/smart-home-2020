package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;

public class SpringConfiguration {

    @Bean
    public SensorEventsManager sensorEventsManager(EventHandler handler) {

        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(new EventHandlerAdapter(handler));
        return sensorEventsManager;
    }
}