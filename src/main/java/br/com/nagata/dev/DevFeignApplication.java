package br.com.nagata.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DevFeignApplication {

  public static void main(String[] args) {
    SpringApplication.run(DevFeignApplication.class, args);
  }
}
