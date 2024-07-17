package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")    // url 경로 /hello에 대한 GET요청
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); // 모델에 data키값에 hello!! 넣어줌, model은 모델 객체를 통해 컨트롤러에서 뷰로 데이터룰 전달하는데 사용
        return "hello";     // 컨트롤러에서 리턴값으로 문자를 반환하면 뷰 리졸버가 화면을 찾아서 처리, resources/templates/{hello}.html
    }

    @GetMapping("hello-mvc")    // url 경로 /hello-mvc에 대한 GET 요청
    public String hellMvc(@RequestParam("name") String name, Model model) { // @RequestParam은 url에 "name=이름" 요청이 들어오면 name 변수에 할당
        model.addAttribute("name",name);    // 컨트롤러가 클라이언트로부터 파라미터를 받아와서 이를 모델에 추가하고 그 모델을 통해 뷰에 전달
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // 뷰를 통해 렌더링하지 않고 직접 HTTP 응답 본문(body)으로 전송
    public String helloString(@RequestParam("name") String name) { // 클라이언트가 URL http://localhost:8080/hello-spring?name=John으로 GET 요청, name에 저장
        return "hello " + name; // name 값 반환
    }

    @GetMapping("hello-api")
    @ResponseBody   // 기본으로 JSON 형식으로 반환
    public Hello helloApi(@RequestParam("name") String name) {  // 요청 파라미터 name 값 받아와 변수에 할당
        Hello hello = new Hello();      // hello 클래스 객체 생성
        hello.setName(name);    // hello 클래스의 setName 메서드 호출 name 값 설정
        return hello;   // 클래스 객체 반환
    }

    static class Hello {
        private String name;
        public String getName() {
            return name;        // @ResponseBody 어노테이션을 쓰면 자동으로 getName 메서드를 호출해서 name 필드 값을 얻어가서 Hello 객체를 JSON형식으로 변환
        }

        public void setName(String name) {  // helloApi에서 설정한 name값 받아옴
            this.name = name;   // Hello클래스의 name 값 helloApi에서 받아온 name 값으로 설정
        }


    }
}
