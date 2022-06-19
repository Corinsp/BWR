package com.bwr.app;

import lombok.Data;

@Data
public class Message {
    private Integer id;
    private Integer sourceId;
    private Integer targetId;
    private Task task;

    public Message(Integer id, Integer sourceId, Integer targetId, Task task) {
        this.id = id;
        this.sourceId = sourceId;
        this.targetId = targetId;
        this.task = task;
    }

    public void sendToRobot(Message message)
    {

    }
}
