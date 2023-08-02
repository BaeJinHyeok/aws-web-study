package com.study.awswebstudy.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드(createDate,ModifiedDate)들도 칼럼들로 인식하도록함. -> Posts가 상속하게됨.
@EntityListeners(AuditingEntityListener.class) //BaseTimeEntity 클래스에 Auditing 기능을 포함시킴 -> ? 잘모르겠음.. 찾아봐야지.. Application 클래스에 어노테이션 추가야함
public class BaseTimeEntity {

    @CreatedDate // Entity가 생성되어 저장될때 시간이 자동저장됨.
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회한 Entity 값을 변경할때 시간이 자동저장됨.
    private LocalDateTime modifiedDate;

}
