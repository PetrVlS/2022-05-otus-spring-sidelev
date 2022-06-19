package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.spring.service.ApplicationRunner;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        var context = SpringApplication.run(Main.class, args);
        var applicationRunner = context.getBean(ApplicationRunner.class);
        applicationRunner.start();
    }
}
