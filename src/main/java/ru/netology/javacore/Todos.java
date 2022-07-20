package ru.netology.javacore;

import java.util.Set;
import java.util.TreeSet;

public class Todos {
    Set<String> tasks = new TreeSet<>();

    public boolean addTask(String task) {
        return tasks.add(task);
    }

    public boolean removeTask(String task) {
        return tasks.remove(task);
    }

    public String getAllTasks() {
        return String.join(" ", tasks);
    }

}
