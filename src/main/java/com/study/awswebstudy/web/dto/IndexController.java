package com.study.awswebstudy.web.dto;

import com.study.awswebstudy.domain.posts.PostsRepository;
import com.study.awswebstudy.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

//    BEFORE
//    @GetMapping("/")
//    public String index(){
//        return "index";
//    }

    private final PostsService postsService;

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);
        return "posts-update";
    }

    @GetMapping("/")
    public String index(Model model) { //Model -> 서버템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음. 여기서는 postsService.finAllDesc()로 가져온 결과를 posts로 index.mustache 에 전달함
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }
}
