package com.todo.app.configuration;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
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
	
	@Value("${jasypt.encryptor.algorithm}")
	String algorithm;

	/**
     * Configures and provides a StringEncryptor bean.
     *
     * @return A configured StringEncryptor.
     */
	@Bean("jasyptEncryptor")
	public StringEncryptor stringEncryptor() {
//	  StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
	  PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
      SimpleStringPBEConfig config = new SimpleStringPBEConfig();
//	  encryptor.setPassword(myKey);
//      encryptor.setAlgorithm(algorithm);
      config.setPassword(myKey); // encryptor's private key
      config.setAlgorithm(algorithm);
      encryptor.setConfig(config);
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

