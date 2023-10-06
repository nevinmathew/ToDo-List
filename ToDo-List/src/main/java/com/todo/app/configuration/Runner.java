package com.todo.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * The `Runner` class is a `CommandLineRunner` component.
 * It is responsible for executing tasks at startup.
 */
@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private EncryptionConfiguration myConfig;

    /**
     * This method is invoked when the application starts.
     * It runs the `printDecryptedPassword` method from the `EncryptionConfiguration` bean.
     *
     * @param args Command-line arguments (not used in this case).
     */
    @Override
    public void run(String... args) {
        myConfig.printDecryptedPassword();
    }
}