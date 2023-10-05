package com.todo.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private EncryptionConfiguration myConfig;

    @Override
    public void run(String... args) {
        myConfig.printDecryptedPassword();
    }
}