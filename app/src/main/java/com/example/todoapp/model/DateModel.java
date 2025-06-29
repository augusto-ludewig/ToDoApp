package com.example.todoapp.model;

public class DateModel {
    private static DateModel instace = new DateModel();
    private DateModel(){

    }

    public static DateModel getInstance(){
        return instace;
    }

    public UserDetails userDetails =
            new UserDetails("augusto","123");
}
