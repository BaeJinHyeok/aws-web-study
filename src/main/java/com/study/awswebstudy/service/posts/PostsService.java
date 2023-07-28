package com.study.awswebstudy.service.posts;

import com.study.awswebstudy.domain.posts.PostsRepository;
import com.study.awswebstudy.web.dto.PostsResponseDto;
import com.study.awswebstudy.web.dto.PostsSaveRequestDto;
import com.study.awswebstudy.web.dto.PostsUpdateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor // Controller와 Serevice에서 @Autowired 를 대체해줌 final이 선언된 모든 필드를 인자값으로 하는 생성자를 대신 생성해줌 -> 의존성 관계가 변경될때마다 생성사 코드를 수정하는 번거로움 해결
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public long update(Long id, PostsUpdateResponseDto responseDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        posts.update(responseDto.getTitle(), responseDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id"+ id));
        return new PostsResponseDto(entity);
    }
}


