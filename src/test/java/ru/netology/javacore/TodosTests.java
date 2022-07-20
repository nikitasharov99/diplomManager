package ru.netology.javacore;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodosTests {
    Todos todos;

    @BeforeEach
    void setUp() {
        todos = new Todos();
    }

    @AfterEach
    void tearDown() {
        todos = null;
    }

    @Test
    void testAddTask() {
        Assertions.assertTrue(todos.addTask("test"));
        Assertions.assertFalse(todos.addTask("test"));
    }

    @Test
    void testRemoveTask() {
        todos.addTask("test");

        Assertions.assertTrue(todos.removeTask("test"));
        Assertions.assertFalse(todos.removeTask("test"));
    }

    @Test
    void testGetAllTasks() {
        String expected = "Акробатика Пробежка Учёба";

        todos.addTask("Учёба");
        todos.addTask("Акробатика");
        todos.addTask("Пробежка");


        String actual = todos.getAllTasks();

        Assertions.assertEquals(expected, actual);
    }
}
