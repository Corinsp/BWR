package com.bwr.app;

import lombok.Data;

@Data
public class Task {
    private Integer id;
    private Status status;
    private String name;
    private String route;
    private String startPoint;


    public Task(Integer id, Status status, String name, String route,String startPoint) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.route = route;
        this.startPoint = startPoint;
    }
}
