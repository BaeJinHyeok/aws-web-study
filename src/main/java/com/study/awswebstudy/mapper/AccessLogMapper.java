package com.study.awswebstudy.mapper;

import com.study.awswebstudy.web.dto.AccessLogDto;

import java.util.List;

@Mapper
public interface AccessLogMapper {
    void create(AccessLogDto accessLogDto);

    List<AccessLogDto> list();
}
