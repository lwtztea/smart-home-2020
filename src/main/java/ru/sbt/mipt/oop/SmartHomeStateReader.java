package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeStateReader {

    public SmartHome readJson(String path) {
        Gson gson = new Gson();
        try {
            String json = new String(Files.readAllBytes(Paths.get(path)));
            return gson.fromJson(json, SmartHome.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not read SmartHome from " + path, e);
        }
    }
}