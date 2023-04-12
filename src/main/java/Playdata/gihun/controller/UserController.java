package Playdata.gihun.controller;

import Playdata.gihun.service.UserService;
import Playdata.gihun.user.UserCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/user") // user로 시작하는 모든 http request요청은 이 컨트롤러에 의해 제어됨
@Controller
@RequiredArgsConstructor // 롬복에서 생성자 만들어주기도 함, 의존성 주입 3가지 방법 중에서 가장 현업에서 많이 쓰는 방법
public class UserController {
    private final UserService userService;
// 유저 서비스 생성자 생성이 되버림
    @GetMapping("/signup") // user/signup 로 접근
    public String signup(UserCreateForm userCreateForm) {
        return "signup_form";
    }
    @PostMapping("/signup") // Postmapping 하는 메소드
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) { // 포스트맵핑 검증 메소드 usercreatrform, bindingresult
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }
        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2","passwordIncorrect","비밀번호와 비밀번호 재확인이 일치하지 않습니다.");
        }

        try {
            userService.create(userCreateForm.getUsername(), userCreateForm.getPassword1(), userCreateForm.getEmail());
        } catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.rejectValue("","signupFailed","이미 가입되어 있는 사용자 입니다.");
            return "signup_form";
        } catch(Exception e) {
            e.printStackTrace();
            bindingResult.rejectValue("","signupFailed",e.getMessage());
            return "signup_form";
        }
        return "redirect:/"; // if문을 거쳐서 생성이되면 메인페이지로 이동
    }
    @GetMapping("/login")
    public String login() {
        return "login_form";
    }
}
