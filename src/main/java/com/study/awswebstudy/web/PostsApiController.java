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
    @DeleteMapping("api/v2/posts/{ids}")
    public String deleteall(@PathVariable List<Long> ids){

        for(Long j: ids) System.out.println(j);
        for(int i =0; i< ids.size(); i++) {
            postsService.delete(ids.get(i));
        }
        return ids.toString(); //"Deleted IDs: " + ids.toString();
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable long id,
                                      @RequestParam (value =  "st", required = false, defaultValue = 1)
                                      @RequestParam (value = "ed", required = false, defaultValue =  10){
        return postsService.findById(id);
    }
}
