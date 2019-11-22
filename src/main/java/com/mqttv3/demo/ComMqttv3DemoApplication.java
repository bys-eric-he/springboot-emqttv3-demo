package com.mqttv3.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.mqttv3.*"})
@SpringBootApplication
public class ComMqttv3DemoApplication {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(ComMqttv3DemoApplication.class, args);
        System.out.println("Running Success !!");
    }
}
