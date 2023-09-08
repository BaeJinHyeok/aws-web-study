package com.study.awswebstudy.domain.user;

import com.study.awswebstudy.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseTimeEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
            
    @Column(nullable = false)
    private String email;
    
    @Column
    private String picture;
    
    @Enumerated(EnumType.STRING) //JPA로 데이터베이스로 저장할때 Enum 값을 어떤 형태로 저장할지를 결정함. 기본적으로는 int로 된 숫자가 저장됨.
                                 //숫자로 저장되면 데이터베이스로 확인할 때 그 값이 무슨 코드를 의미하는 지 알 수 없기 때문에 문자열(EnumType.STRING)로 저장될 수 있도록 선언함.

    @Column(nullable = false)
    private UserRole userrole;
    
    @Builder
    public Users(String name, String email, String picture, UserRole userrole) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.userrole = userrole;
    }
    
    public Users update(String name, String picture){
        this.name = name;
        this.picture = picture;
        
        return this;
    }
    
    public String getRoleKey() {
        return this.userrole.getKey(); // Role role 사용시 다른 클래스와 충돌 때문에 getKey() 명령어가 입력안됐었음. 현재 Role -> UserRole로 변경 후 입력 잘 됨.
    }
}
