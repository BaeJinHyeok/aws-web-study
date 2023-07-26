package com.study.awswebstudy.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat; //junit의 assertThat이 아닌 assertj 라이브러리 사용
//장점은 1. 추가적인 라이브러리 필요하지않음, 자동완성이 확실하게 지원 됨
import static org.junit.jupiter.api.Assertions.*;

class HelloResponseDtoTest {

    //@Getter로 get 메소드, @RequiredArgsConstructor로 생성자가 자동 생성되는 것을 확인
    @Test
    public void  롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        //then
        assertThat(dto.getName()).isEqualTo(name); //검증하고 싶은 메소드 인자 받음. 메소드 체이닝 지원으로 isEqualTo 이어사용가능
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}