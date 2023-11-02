package com.study.awswebstudy.mapper;

import com.study.awswebstudy.web.dto.AccessLogDto;

import java.util.List;

public class AccessLogMapper {

    void create(AccessLogDto accessLogDto);

    List<AccessLogDto> list();
}
