package com.example.todoapp.model;

import java.util.ArrayList;

public class DataModel {
    private static DataModel instace = new DataModel();
    private DataModel(){
        tarefas.add(
                new Tarefa()
        );
    }

    public static DataModel getInstance(){
        return instace;
    }

    public UserDetails userDetails =
            new UserDetails("augusto","123");

    public ArrayList<Tarefa> tarefas = new ArrayList<>();
}
