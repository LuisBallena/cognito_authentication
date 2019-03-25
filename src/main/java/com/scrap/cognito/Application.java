package com.scrap.cognito;

import com.scrap.microservice.annotation.EnabledMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application.
 *
 * @author Luis Alonso Ballena Garcia
 */
@EnabledMessage
@SpringBootApplication
public class Application {


    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

}
