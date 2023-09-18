package com.study.awswebstudy.web;

import com.study.awswebstudy.service.posts.PostsService;
import com.study.awswebstudy.web.dto.PostsResponseDto;
import com.study.awswebstudy.web.dto.PostsSaveRequestDto;
import com.study.awswebstudy.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public long update(@PathVariable long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }
    @DeleteMapping("api/v1/posts/{id}")
    public long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
//    @DeleteMapping("api/v1/posts/{ids}")
//    public long deleteall(@PathVariable List<Long> ids){
//
//        for(int i =0; i< ids.size(); i++) {
//            postsService.delete(ids[i]);
//        }
//        return "Deleted IDs: " + ids.toString();
//    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable long id){
        return postsService.findById(id);
    }
}
