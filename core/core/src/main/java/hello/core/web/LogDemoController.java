package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger; // ObjectProvider를 사용해서 myLogger를 찾을 수있는 DL이 주입된다

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) { // HttpServletRequest 자바에서 제공하는 표준 서블릿 규약, 고객 요청 정보를 받을 수 있음
        String requestURL = request.getRequestURI().toString(); // requestURL 값 http://localhost:8080/log-demo
        System.out.println("myLogger = " + myLogger.getClass()); //myLogger = class hello.core.common.MyLogger$$SpringCGLIB$$0 가짜 생성 확인

        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
