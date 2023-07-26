package com.study.awswebstudy.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// Posts 클래스로 DB에 접근하게 해줄 JpaRepository. 보통 ibatus 나 MyBatis 등에서 Dao 라고 불리는 DB Layer 접근자. Jpa에선 Repository라고 부르며 인터페이스로 생성함.
// *주의* Entity 클래스와 Entity Repository 는 함께 위치해야함. 프로젝트 규모가 커져 도메인별로 프로젝트를 분리해야한다면 Entity 클래스와 기본 Repository느 도메인 패키지에서 함께관리함.
public interface PostsRepository extends JpaRepository<Posts, Long> { // JapRe...<Entity 클래스, PK 타입>를 상속하면 기본적인 CRUD 메소드가 자동생성됨. @Repository 추가안해도됨.

}
