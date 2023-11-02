package com.todo.app.configuration;

import javax.sql.DataSource;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Configuration class for encryption settings.
 */
@Configuration
public class EncryptionConfiguration  {

	@Value("${jasypt.encryptor.password}")
	String myKey;

	/**
     * Configures and provides a StringEncryptor bean.
     *
     * @return A configured StringEncryptor.
     */
//	@Bean
//	public StringEncryptor stringEncryptor() {
//	  StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
//	  encryptor.setPassword(myKey);
//	  return encryptor;
//	}
	
	/**
     * Configures and provides a DataSource bean.
     *
     * @return A configured DataSource.
     */
	@Bean
    public DataSource dataSource(Environment environment) {
        String encryptedPassword = environment.getProperty("spring.datasource.password");
        String password = encryptor().decrypt(encryptedPassword);

        return DataSourceBuilder.create()
                .url(environment.getProperty("spring.datasource.url"))
                .username(environment.getProperty("spring.datasource.username"))
                .password(password)
                .build();
    }

    @Bean
    public StandardPBEStringEncryptor encryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(myKey);
        return encryptor;
    }

	/**
     * Print the decrypted password.
     */
    public void printDecryptedPassword() {
        try{
        	String encryptedPassword = "I0BXUCm0vCiASShRUucwIA==";
        	System.out.println("decryption key: " + myKey);
	        String decryptedPassword = encryptor().decrypt(encryptedPassword);
	        System.out.println("Decrypted Password: " + decryptedPassword);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}

