package com.study.awswebstudy.web;

import com.study.awswebstudy.domain.Pages.Pages;
import com.study.awswebstudy.web.dto.PostsResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public class PostsController {
    final private Pages pages
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable long id,
                                      @RequestParam(value =  "st", required = false, defaultValue = "1") Integer offset,
                                      @RequestParam (value = "ed", required = false, defaultValue =  "10") Integer limit){

        offset = pages.setStart();
        limit = pages.setLimit();
        int total = pages.setTotal();
        return .findById(id);
    }
}
