package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")        // 루트 url 매핑
    public String home() {
        return "home";      // home.html 랜더링
    }
}
