package com.suhyeokeeee.tistory.firstpjt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  //jpa Auditing 활성화
@SpringBootApplication
public class FirstpjtApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstpjtApplication.class, args);
    }

}
