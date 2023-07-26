package com.study.awswebstudy.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HelloResponseDto {
    //annotationProcessor 'org.projectlombok:lombok'
    //implementation 'org.projectlombok:lombok'
    //testAnnotationProcessor 'org.projectlombok:lombok'
    //testImplementation 'org.projectlombok:lombok'
    //위 의존성 gradle에 추가 후 @RAC 사용가능
    private final String name;
    private final int amount;
   }
