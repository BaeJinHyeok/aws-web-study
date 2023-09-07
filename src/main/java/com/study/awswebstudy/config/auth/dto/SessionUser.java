package com.study.awswebstudy.config.auth.dto;

import com.study.awswebstudy.domain.user.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor // SessionUser 에서 user.getName() 에 대한 NullException error 생성으로 인해 무한 리다이렉트-> 접속안됨 해결. 하지만 google 로그인이 안됨..? userName이 없는 건 해결안됨..
@Getter
public class SessionUser implements Serializable { // SessionUser 에는 인증된 사용자 정보만 필요함 그 외의 정보필요는 없으니 name,email.picture 만 필드로 선언

    private String name;
    private String email;
    private String picture;

    public SessionUser(Users users) {
        this.name = users.getName();
        this.email = users.getEmail();
        this.picture = users.getPicture();
    }

}
