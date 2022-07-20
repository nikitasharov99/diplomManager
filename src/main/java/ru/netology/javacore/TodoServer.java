package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private final int port;
    private final Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Starting server at " + port + "...");
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream())) {
                    String jsonRequest = in.readLine();
                    Gson gson = new Gson();
                    Request request = gson.fromJson(jsonRequest, Request.class);
                    switch (request.getType()) {
                        case "ADD":
                            if (todos.addTask(request.getTask())) {
                                System.out.println("Добавлена задача " + request.getTask());
                            } else {
                                System.out.println("Невозможно добавить задачу " +
                                        request.getTask() +
                                        " так как она уже существует");
                            }
                            break;
                        case "REMOVE":
                            if (todos.removeTask(request.getTask())) {
                                System.out.println("Удалена задача " + request.getTask());
                            } else {
                                System.out.println("Невозможно удалить задачу " +
                                        request.getTask() + " так как она не запланирована");
                            }
                            break;
                    }
                    out.println(todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("Невозможно запустить сервер");
            e.printStackTrace();
        }
    }
}
