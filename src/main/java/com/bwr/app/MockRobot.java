package com.bwr.app;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class MockRobot {

    public static Integer robotId = 1; //target
    private static Map<Integer,Task> tasks = new HashMap<>();
    private static Map<Integer,Task>  activeTasks = new HashMap<>();
    private static String response = "Received";

    private static Map<Integer, Robot> robots = new HashMap<>();
    private static Map<Integer, Robot> activeRobots = new HashMap<>();


    public static Boolean isContainRobot(Integer robotId)
    {
        if(activeRobots.containsKey(robotId))
            return true;
        return false;
    }
    public static Robot getActiveRobot(Integer robotId)
    {
        return activeRobots.get(robotId);
    }

    public static String getTask(Message message)
    {
        tasks.put(message.getTask().getId(),message.getTask());
        System.out.println("Uploading task...");
        return response;
    }
    public static String startTask(Message message)
    {
        activeTasks.put(message.getTask().getId(),message.getTask());
        System.out.println("Starting task...");
        return response;
    }
    public static String stopTask(Message message)
    {
        activeTasks.put(message.getTask().getId(),message.getTask()); //change task status
        System.out.println("Stopping task...");
        return response;
    }
    public static String endTask(Message message)
    {
        Task task = activeTasks.get(message.getTask().getId());
        task.setStatus(Status.DONE);
        tasks.put(message.getTask().getId(),task);
        activeTasks.remove(message.getTask().getId());
        return response;
    }

    public static void addActiveRobot(Robot robot) {
        activeRobots.put(robot.getId(),robot);
    }

    public static void removeFromActiveRobots(Integer robotId) {
        Robot robot = activeRobots.get(robotId);
        robot.setState(State.OFF);
        robots.put(robotId,robot);
    }

    public static void setLastUpdateTime(Integer robotId, Timestamp now) {
        Robot robot = activeRobots.get(robotId);
        robot.setLastUpdateTime(now);
        activeRobots.put(robotId,robot);
    }
}
