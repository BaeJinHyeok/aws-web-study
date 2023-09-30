package com.study.awswebstudy.mapper;

import com.study.awswebstudy.web.dto.PostsSaveRequestDto;
import com.study.awswebstudy.web.dto.PostsUpdateRequestDto;

@Mapper
public interface PostsMapper {

    long save(PostsSaveRequestDto requestDto) throws Exception;

    long update(Long id, PostsUpdateRequestDto responseDto) throws Exception;
}
