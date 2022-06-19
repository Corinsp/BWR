package com.bwr.app;

import com.bwr.db.Tasks;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {

    private static AtomicInteger incId = new AtomicInteger(0);
    private static AtomicInteger msgId = new AtomicInteger(0);
    private static Integer sourceId = 111;

    @PostMapping("/task/upload")
    public void uploadTask(@RequestParam String taskName, @RequestParam String route, @RequestParam String startPoint) {
        Task task = new Task(incId.incrementAndGet(), Status.NEW, taskName, route, startPoint);
        Tasks.addTask(task);
        Message message = new Message(msgId.incrementAndGet(), sourceId, MockRobot.robotId, task);
        MockRobot.getTask(message);

    }

    @PostMapping("/task/start")
    public void startTask(@RequestParam Integer taskId) {
        Task task = Tasks.getTask(taskId);
        task.setStatus(Status.IN_PROGRESS);
        Tasks.startTask(task);
        Message message = new Message(msgId.incrementAndGet(), sourceId, MockRobot.robotId, task);
        MockRobot.startTask(message);
        System.out.println("Starting to work on: " + task);
    }

    @PostMapping("/task/stop")
    public void stopTask(@RequestParam Integer taskId) {
        Task task = Tasks.getActiveTask(taskId);
        task.setStatus(Status.PENDING);
        Tasks.updateStatus(task);
        Message message = new Message(msgId.incrementAndGet(), sourceId, MockRobot.robotId, task);
        MockRobot.stopTask(message);
        System.out.println("Stopping task: " + task);
    }

    @PostMapping("/task/end")
    public void endTask(@RequestParam Integer taskId) {
        Task task = Tasks.getActiveTask(taskId);
        Tasks.removeTask(taskId);
        Message message = new Message(msgId.incrementAndGet(), sourceId, MockRobot.robotId, task);
        MockRobot.endTask(message);
    }

    @GetMapping("/task") //testing purpose
    public String doSome(@RequestParam Integer id) {
        return "id: " + id;
    }

    @GetMapping("/robot")
    public String isAlive(@RequestParam Integer robotId) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        long diff = now.getTime()/1000 - MockRobot.getActiveRobot(robotId).getLastUpdateTime().getTime()/1000;

        MockRobot.setLastUpdateTime(robotId,now);
        if ( diff > 10) {
            MockRobot.removeFromActiveRobots(robotId);
            return "Turning off Robot id: " + robotId;
        }
        else
            return "Robot id: " + robotId + " is alive --> " + MockRobot.getActiveRobot(robotId);

    }
}
