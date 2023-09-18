package com.study.awswebstudy.domain.posts;

import com.study.awswebstudy.domain.user.Users;
//import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.*;
import java.util.List;

// Posts 클래스로 DB에 접근하게 해줄 JpaRepository. 보통 ibatus 나 MyBatis 등에서 Dao 라고 불리는 DB Layer 접근자. Jpa에선 Repository라고 부르며 인터페이스로 생성함.
// *주의* Entity 클래스와 Entity Repository 는 함께 위치해야함. 프로젝트 규모가 커져 도메인별로 프로젝트를 분리해야한다면 Entity 클래스와 기본 Repository느 도메인 패키지에서 함께관리함.
public interface PostsRepository extends JpaRepository<Posts, Long> { // JapRe...<Entity 클래스, PK 타입>를 상속하면 기본적인 CRUD 메소드가 자동생성됨. @Repository 추가안해도됨.

    //SpringDataJpa에서 제공하지 않는 메소드는 이렇게 사용가능. 이 코드는 기존 SpringDataJpa에서 제공하지만 @Query가 훨씬 가독성이 좋긴함.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
//    Page<Posts> findByUserOrderByIdDesc(Users user, Pageable pageable);

    // 규모가 있는 프로젝트에서는 조회용 프레임워크를 추가로 사용.
    // 예로 querydsl, jooq, MyBatis 등이 있음
    // querydsl 추천. 1. 타입안정성이 보장됨 - 메소드를 기반으로 쿼리를 생성하기 때문에 오타나 존재하지않는 컬럼명을 명시할 경우 IDE에서 자동검출됨 -> jooq는 지원 MyBatis는 지원 안함
    // 2. 국내 많은 회사에서 사용중 3. 레퍼런스가 많음.
}
