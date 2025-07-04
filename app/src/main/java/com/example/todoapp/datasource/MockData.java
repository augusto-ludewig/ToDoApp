package com.example.todoapp.datasource;

import com.example.todoapp.model.entities.User;
import com.example.todoapp.model.entities.Tarefa;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MockData {
    private static MockData instace = new MockData();
    public ArrayList<Tarefa> tarefas = new ArrayList<>();
    private MockData(){
        // Dados fictícios iniciais
        tarefas.add(new Tarefa(
                "Estudar Java",
                "Focar em orientação a objetos",
                LocalDateTime.parse("2025-07-03T23:10:45.123"),
                false,
                false));

        tarefas.add(new Tarefa(
                "Fazer trabalho",
                "Terminar o projeto da faculdade",
                LocalDateTime.parse("2025-07-04T21:10:45.123"),
                false,
                false));

        tarefas.add(new Tarefa(
                "Fazer trabalho",
                "Terminar o projeto da faculdade",
                LocalDateTime.parse("2025-07-04T21:10:45.123"),
                false,
                false));

    }

    public static MockData getInstance(){
        return instace;
    }

    public User user =
            new User("augusto","123");

}
