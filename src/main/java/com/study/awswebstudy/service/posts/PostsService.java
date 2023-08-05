package com.study.awswebstudy.service.posts;

import com.study.awswebstudy.domain.posts.Posts;
import com.study.awswebstudy.domain.posts.PostsRepository;
import com.study.awswebstudy.web.dto.PostsResponseDto;
import com.study.awswebstudy.web.dto.PostsSaveRequestDto;
import com.study.awswebstudy.web.dto.PostsUpdateRequestDto;
import com.study.awswebstudy.web.dto.PostsListResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // Controller와 Serevice에서 @Autowired 를 대체해줌 final이 선언된 모든 필드를 인자값으로 하는 생성자를 대신 생성해줌 -> 의존성 관계가 변경될때마다 생성사 코드를 수정하는 번거로움 해결
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public long update(Long id, PostsUpdateRequestDto responseDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        posts.update(responseDto.getTitle(), responseDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) //  readOnly -> 트랜잭션 범위는 유지하되, 조회기능만 남겨두어 조회속도가 개선됨.  등록,수정,삭제가 없는 서비스 메소드에서 사용 추천
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // .map(posts -> new PostsListResponseDto(posts)) 와 같음.
                                                // postRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostListResponseDto 변환 -> List로 변환하는 메소드임.
                .collect(Collectors.toList());
    }
}


