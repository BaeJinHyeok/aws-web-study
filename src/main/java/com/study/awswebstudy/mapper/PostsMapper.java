package com.study.awswebstudy.mapper;

import com.study.awswebstudy.web.dto.PostsSaveRequestDto;

@Mapper
public interface PostsMapper {

    long save(PostsSaveRequestDto requestDto) throws Exception;
}
