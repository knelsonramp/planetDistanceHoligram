package com.example.nuclearfissioncore.commands;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class TestCommand implements CommandLineRunner {

    private static final String COMMAND_NAME = "kevin:testCommand";

    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello world");
    }
}
