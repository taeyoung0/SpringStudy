package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")    // url 경로 /hello에 대한 GET요청
    public String hello(Model model) {
        model.addAttribute("data", "spring!!"); // 모델에 data키값에 hello!! 넣어줌
        return "hello";     // 컨트롤러에서 리턴값으로 문자를 반환하면 뷰 리졸버가 화면을 찾아서 처리, resources/templates/{hello}.html
    }
}
