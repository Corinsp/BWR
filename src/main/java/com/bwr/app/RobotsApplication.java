package com.bwr.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

import java.sql.Timestamp;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class RobotsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RobotsApplication.class, args);
		Robot robot = new Robot(1,State.ON,new Timestamp(System.currentTimeMillis()));
		MockRobot.addActiveRobot(robot);
	}
}
