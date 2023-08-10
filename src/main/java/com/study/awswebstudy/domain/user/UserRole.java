package com.study.awswebstudy.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    GUEST("ROLE_GUEST", "손님"),           //private final String key; 로 GUEST(key,title) 적용 밑도 동일.
    USER("ROLE_USER", "일반 사용자");

    private final  String key;
    private final  String title;
}
