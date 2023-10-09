package com.todo.app.configuration;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	@Bean
	public StringEncryptor stringEncryptor() {
	  StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
	  encryptor.setPassword("${MY_KEY_VAR}");
	  return encryptor;
	}

	/**
     * Print the decrypted password.
     */
    public void printDecryptedPassword() {
        try{
        	String encryptedPassword = "I0BXUCm0vCiASShRUucwIA==";
        	System.out.println("decryption key: " + myKey);
	        String decryptedPassword = stringEncryptor().decrypt(encryptedPassword);
	        System.out.println("Decrypted Password: " + decryptedPassword);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}

