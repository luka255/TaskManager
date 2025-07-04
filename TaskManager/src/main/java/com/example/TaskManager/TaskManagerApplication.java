package com.example.TaskManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
@EnableAsync
public class TaskManagerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(TaskManagerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

}
