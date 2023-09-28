package com.study.awswebstudy.service.posts;

import com.study.awswebstudy.web.dto.PostsListResponseDto;
import com.study.awswebstudy.web.dto.PostsResponseDto;
import com.study.awswebstudy.web.dto.PostsSaveRequestDto;

import java.util.List;

public interface PostsService {

    long save(PostsSaveRequestDto requestDto) throws Exception;
    long update(Long id, PostsSaveRequestDto requestDto) throws Exception;
    void delete(Long id) throws Exception;
    PostsResponseDto findById(Long id) throws Exception;
    List<PostsListResponseDto> findAllDesc() throws Exception;

}
