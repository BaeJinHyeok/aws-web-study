package com.study.awswebstudy.web;

import com.study.awswebstudy.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    //@RequestParam 어노테이션 > 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
    //@RequestParam("name") 이란 이름으로 넘긴 파라미터를 매소드 파라미터 (String name) 에 저장하게 됨(HelloReponseDto)
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}
