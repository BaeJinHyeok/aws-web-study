package com.study.awswebstudy.domain.posts;

import com.study.awswebstudy.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter //lombok 클래스 내 모든 필드의 Getter 메소드를 자동생성.
@NoArgsConstructor //lombok 기본 생성자 자동 추가 public Posts(){} 와 같은 효과
@Entity // *주요* JPA 어노테이션 테이블과 링크될 클래스임을 나타냄. 카멜케이스 이름을 언더스코어네이밍(_) 으로 테이블이름을 매칭함.
// Entity 클래스에서는 절대 Setter 메소드를 만들지 않는걸 권장.
// 대신 해당 필드의 값이 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야만 함. 차후 기능 변경 시 복잡해지기 때문.
public class Posts  extends BaseTimeEntity { //실제 DB의 테이블과 매칭될 클래스. Entity 클래스.

    @Id // 해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성규칙을 나타냄 Springboot 2.0에서는 추가해야만 auto increment
    private Long id;

    @Column(length = 500, nullable = false) // 테이블의 칼럼을 나타냄. 선언안해도 해당 클래스의 필드는 모두 컬럼이 됨. 사용 이유는 기본값 외에 옵션이 있을경우 사용. 문자열 사이즈 500늘리기.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) // 타입을 TEXT 로 변경
    private String content;

    private String author;

    private String delYn;       //삭제여부

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성. 생성자 상단 선언 시 생성자에 포함한 필드만 빌더에 포함
    public Posts(String title, String content, String author, String delYn){
        this.title = title;
        this.content = content;
        this.author = author;
        this.delYn = "N";
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Posts delete(String delYn){
        this.delYn = delYn;
        return this;
    }
}
