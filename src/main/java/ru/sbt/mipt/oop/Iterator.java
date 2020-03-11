package ru.sbt.mipt.oop;

import java.util.function.Function;

public interface Iterator {

    Object each(Function func);
}
