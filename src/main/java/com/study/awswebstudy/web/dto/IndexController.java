package com.study.awswebstudy.web.dto;

//import com.study.awswebstudy.config.auth.dto.LoginUser;
import com.study.awswebstudy.Util.Cookies;
import com.study.awswebstudy.config.auth.dto.SessionUser;
import com.study.awswebstudy.service.posts.PostsService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

//import java.awt.print.Pageable;

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

//    @GetMapping("/posts")
//    public String find(Pageable pageable){
//        postsService.findAll(pageable);
//        return "/";
//    }
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
    public String  index(Model model, HttpServletRequest request, HttpServletResponse response, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {//, @LoginUser SessionUser user) { //Model -> 서버템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음. 여기서는 postsService.finAllDesc()로 가져온 결과를 posts로 index.mustache 에 전달함

        Cookies cookies = new Cookies(request);

        Cookie createCk;
        try {
            createCk = cookies.createCookie("test2", "testtt","localhost","/", 60);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Cookie >>>>>>" + cookies.getCookie("test"));
        try {
            System.out.println("Cookie >>>>>>" + cookies.getValue("test"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        boolean delYn = true;

        model.addAttribute("posts", postsService.findAllDesc());

        SessionUser user = (SessionUser) httpSession.getAttribute("user"); // 앞서 작성된 CustomOAuthUserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성.
        // -> 세션값을 가지고 오는 부분 -> 같은 코드가 반복되는 부분! 이후에 수정이 필요하면 모든 부분을 찾아가며 수정해야함 -> 유지보수성 문제
        // -> index 메소드 외에 다른 컨트롤러와 메소드에서 세션값이 필요하면 그 때마다 직접 세션에서 값을 가지고와야함 -> 같은 코드 반복 -> 불필요
        // -> 이부분을 메소드 인자로 세션값을 바로 받을 수 있도록 변경 ! -> config.auth 패키지에 @LoginUser 어노테이션 생성
        // 세션값 가져오는 부분 삭제. 밑 코드 추가
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(authentication);
        System.out.println(authentication.getPrincipal());

        // 인증되지 않은 사용자라면 경고 메시지를 모델에 추가합니다.
        if (authentication.getPrincipal() == "anonymousUser" || authentication == null || !authentication.isAuthenticated()) {
            model.addAttribute("warningMessage", "로그인이 필요합니다.");
        } else {
            model.addAttribute("warningMessage", "로그인이 됐습니다.");
        }

        System.out.println(model.getAttribute("warningMessage"));

        if (user != null) { //세션에 저장된 값이 있을때만 model 에 userName으로 등록함. 세션에 저장된 값이 없으면 model엔 아무런 값이 없는 상태이니 로그인 버튼이 보이게됨.

            System.out.println(user.getName() + user.getEmail() + user.getPicture() + "NOTNULL");
            model.addAttribute("userName", user.getName());

            //로그인 사용자 userName 이 있을 경우 체크박스 활성화
            if (delYn) {
                delYn = false;
            }
        }

        model.addAttribute("delYn", delYn);
        System.out.println(model.getAttribute("delYn") + "  delYn ");
        System.out.println(model.getAttribute("userName") + "NULL ");

        model.addAttribute("pageable", pageable);
        model.addAttribute("cook", createCk);
        System.out.println("createCk"+createCk);
        response.addCookie(createCk);
        //response.setHeader("Set-Cookie","token=" + "TEST" +. ;Path=/; Domain=localhost; HttpOnly; Max-Age=60; SameSite=None; Secure;");
        Map<String, Cookie> cookieMap = new java.util.HashMap<String, Cookie>();
        Cookie[] cookiesTest = request.getCookies();
        if(cookiesTest != null){
            for(int i = 0; i < cookiesTest.length; i++){
                cookieMap.put(cookiesTest[i].getName(), cookiesTest[i]);
            }
        }
        System.out.println("Cookie MAP>>>>>"+cookieMap);
        System.out.println("Cookie List>>>>>>" +cookiesTest);
        System.out.println("cookiesTest>>>>>"+cookiesTest);
        System.out.println("exists>>>>>"+cookies.exists("test"));
        try {
            System.out.println(URLDecoder.decode(cookieMap.toString(), "euc-kr"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println("생성 후 Cookie >>>>>>" + cookies.getValue("test2"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(model.getAttribute("pageble"));
        return "index";
    }
}
