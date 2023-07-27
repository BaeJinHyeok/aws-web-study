package com.study.awswebstudy.web.dto;

// Controller 와 Service에서 사용할 Dto 클래스를 생성
// Entity 클래스(Posts) 와 유사한 형태임에도 Dto 클래스를 추가로 구성함. 허나 절대 Entity클래스를 Request/Response 클래스로 사용해선 안됨
// 이유 -> Entity 클래스는 데이터베이스와 맞닿는 핵심클래스임(테이블 생성,스키마변경이이 일어남). 화면 변경은 아주 사소한 변경인데 Entity 클래스를 변경하는건 너무 큰 변경이기 때문! 변경 시 여러클래스에 영향을 끼침.
// View Layer와 DB Layer의 역할 분리 > Request와 Response용 Dto는 View를 위한 클래스가 자주 변경이 필요하기 때문
// Entity 클래스와 Controller에서 쓸 Dto는 분리해서 사용해야 함.
import com.study.awswebstudy.domain.posts.Posts;
import com.study.awswebstudy.domain.posts.PostsRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content ;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}

