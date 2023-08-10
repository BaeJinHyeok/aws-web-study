package com.study.awswebstudy.domain.user;

import com.study.awswebstudy.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
            
    @Column(nullable = false)
    private String email;
    
    @Column
    private String picture;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userrole;
    
    @Builder
    public User(String name, String email, String picture, UserRole userrole) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.userrole = userrole;
    }
    
    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;
        
        return this;
    }
    
    public String getRoleKey() {
        return this.userrole.getKey(); // Role role 사용시 다른 클래스와 충돌 때문에 getKey() 명령어가 입력안됐었음. 현재 Role -> UserRole로 변경 후 입력 잘 됨.
    }
}
