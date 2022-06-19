package com.bwr.db;

import com.bwr.app.Status;
import com.bwr.app.Task;
import java.util.HashMap;
import java.util.Map;

public class Tasks {

    private static Map<Integer, Task> allTasks= new HashMap<>();
    private static Map<Integer, Task> activeTasks= new HashMap<>();


    public static void addTask(Task task)
    {
        allTasks.put(task.getId(),task);
    }
    public static void removeTask(Integer taskID)
    {
        if (activeTasks.containsKey(taskID))
        {
            Task task = activeTasks.get(taskID);
            task.setStatus(Status.DONE);
            allTasks.put(taskID,task);
            activeTasks.remove(taskID);
        }
    }
    public static Task getActiveTask(Integer taskID)
    {
        return activeTasks.get(taskID);
    }

    public static void updateStatus(Task task) {
        activeTasks.put(task.getId(), task);
    }

    public static void startTask(Task task) {
        activeTasks.put(task.getId(),task);
    }

    public static Task getTask(Integer taskId) {
        return allTasks.get(taskId);
    }
}
