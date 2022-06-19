package com.bwr.db;

import com.bwr.app.Robot;

import java.util.Map;

public class AllRobots {

    private Map<Integer, Robot> robots;

     public Map<Integer, Robot> getAllRobots()
     {
         return this.robots;
     }

     public Robot getRobot(int robotId)
     {
         return this.robots.get(robotId);
     }
}
