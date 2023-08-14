package com.study.awswebstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화 -> BaseTimeEntity
@SpringBootApplication
public class AwsWebStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsWebStudyApplication.class, args);

        String test = org.springframework.core.SpringVersion.getVersion();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Spring version :"+ test);
    }

}
