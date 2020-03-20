package br.com.ottimizza.statusreportapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StatusreportApiApplication {

    public static void main(String[] args) {
            SpringApplication.run(StatusreportApiApplication.class, args);
    }
}