package com.todo.app;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@EnableCaching
//@EnableEncryptableProperties
@SpringBootApplication
public class ToDoListApplication {

//	@Value("${jasypt.encryptor.password}")
//	String myKey;
	
	public static void main(String[] args) {
//		String myKey = System.getenv("MY_KEY_VAR");
//        System.out.println("MY_KEY_VAR: " + myKey);
		
		SpringApplication.run(ToDoListApplication.class, args);
	}

}
