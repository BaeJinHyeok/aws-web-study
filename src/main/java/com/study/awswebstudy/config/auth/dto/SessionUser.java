package com.study.awswebstudy.config.auth.dto;

import com.study.awswebstudy.domain.user.User;
import lombok.Getter;

@Getter
public class SessionUser { // SessionUser 에는 인증된 사용자 정보만 필요함 그 외의 정보필요는 없으니 name,email.picture 만 필드로 선언
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }

}
