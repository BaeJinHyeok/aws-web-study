package com.study.awswebstudy.web;

import com.study.awswebstudy.service.posts.PostsServiceImpl;
import com.study.awswebstudy.web.dto.PostsResponseDto;
import com.study.awswebstudy.web.dto.PostsSaveRequestDto;
import com.study.awswebstudy.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsServiceImpl postsServiceImpl;

    @PostMapping("/api/v1/posts")
    public long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsServiceImpl.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public long update(@PathVariable long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsServiceImpl.update(id, requestDto);
    }
    @DeleteMapping("api/v1/posts/{id}")
    public long delete(@PathVariable Long id){
        postsServiceImpl.delete(id);
        return id;
    }
    @DeleteMapping("api/v2/posts/{ids}")
    public String deleteall(@PathVariable List<Long> ids){

        for(Long j: ids) System.out.println(j);
        for(int i =0; i< ids.size(); i++) {
            postsServiceImpl.delete(ids.get(i));
        }
        return ids.toString(); //"Deleted IDs: " + ids.toString();
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable long id){
        return postsServiceImpl.findById(id);
    }
}
