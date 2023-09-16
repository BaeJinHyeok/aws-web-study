package com.study.awswebstudy.web.dto;

//import com.study.awswebstudy.config.auth.dto.LoginUser;
import com.study.awswebstudy.config.auth.dto.SessionUser;
import com.study.awswebstudy.domain.posts.PostsRepository;
import com.study.awswebstudy.service.posts.PostsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final HttpSession httpSession;

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
    public String  index(Model model){//, @LoginUser SessionUser user) { //Model -> 서버템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음. 여기서는 postsService.finAllDesc()로 가져온 결과를 posts로 index.mustache 에 전달함

        boolean required = true;

        model.addAttribute("posts", postsService.findAllDesc());

        SessionUser user = (SessionUser) httpSession.getAttribute("user"); // 앞서 작성된 CustomOAuthUserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성.
        // -> 세션값을 가지고 오는 부분 -> 같은 코드가 반복되는 부분! 이후에 수정이 필요하면 모든 부분을 찾아가며 수정해야함 -> 유지보수성 문제
        // -> index 메소드 외에 다른 컨트롤러와 메소드에서 세션값이 필요하면 그 때마다 직접 세션에서 값을 가지고와야함 -> 같은 코드 반복 -> 불필요
        // -> 이부분을 메소드 인자로 세션값을 바로 받을 수 있도록 변경 ! -> config.auth 패키지에 @LoginUser 어노테이션 생성
        // 세션값 가져오는 부분 삭제. 밑 코드 추가

        if(user != null) { //세션에 저장된 값이 있을때만 model 에 userName으로 등록함. 세션에 저장된 값이 없으면 model엔 아무런 값이 없는 상태이니 로그인 버튼이 보이게됨.

            System.out.println(user.getName() + user.getEmail() + user.getPicture()+"NOTNULL");
            model.addAttribute("userName", user.getName());

            if(required){
                required = false;
            }
        }

        model.addAttribute("required", required);

        System.out.println(model.getAttribute("userName")+"NULL ");
        return "index";
    }
}
