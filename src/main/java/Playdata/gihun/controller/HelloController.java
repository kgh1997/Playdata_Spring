
package Playdata.gihun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 컨트롤러라는 로직으로 동작하게끔 명시를 해놓은 것임 알아서 다 스프링이 주입함
public class HelloController {
    // 사랑해
    @GetMapping("Hello") // Get: HTTP통신할 때 요청, Hello page 요청하면 Hello의 메세지가 동작합니다.
    public String Hello_(Model model){
        model.addAttribute("name","김기훈");
        return "Hello"; // 여기 Hello는 왼쪽 바에서 Hello를 의미함 (즉, Hello page에 모델이라는 값이 넘어오게 되는것임)
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name_value, Model model){
        model.addAttribute("name", name_value);
        return "hello-template";
    }
    // 인자 값 2개를 받는 메서드를 작성하고, View로 렌더링 (name-문자열, age-정수)
    @GetMapping("hello-mvc2")
    public String helloMvc2(@RequestParam("name") String name, @RequestParam("age") Integer age, Model model){
        model.addAttribute("name", name);
        model.addAttribute("age",age);
        return "hello-template";
    }
    // 네이버 검색창에 seach?q= 이런느낌으로 만들어보기
    @GetMapping("form-test")
    public String formTest() {
        return "form-test";
    }
    //@GetMapping("form-test-get")
    @PostMapping("form-test-get")
    public String formTestGet(@RequestParam("username") String username_value,
                              @RequestParam("password") String password_value, Model model) {
        model.addAttribute("username",username_value);
        model.addAttribute("password",password_value);
        return "hello-template2";
    }
    // API서버 이용할 때 간단하게 jSON형태로 전달함
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name_value) {
        return "안녕하세요? 반환된 너의 이름은?" + name_value;
    }
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name_value) {
        Hello hello = new Hello();
        hello.setName(name_value);
        return hello;
    }
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

    }
    @GetMapping("/")
    public String root() {
        return "index";
    }
}
