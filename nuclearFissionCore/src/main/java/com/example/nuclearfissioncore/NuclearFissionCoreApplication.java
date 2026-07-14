package com.example.nuclearfissioncore;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Arrays;

@SpringBootApplication
public class NuclearFissionCoreApplication {

    public static void main(String[] args) {
        boolean runningCommand = Arrays.stream(args).anyMatch(arg -> arg.contains(":"));

        new SpringApplicationBuilder(NuclearFissionCoreApplication.class)
                .web(runningCommand ? WebApplicationType.NONE : WebApplicationType.SERVLET)
                .run(args);
    }

}
