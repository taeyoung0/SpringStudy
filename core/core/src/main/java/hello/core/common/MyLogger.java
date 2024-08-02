package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // request를 사용하면 HTTP요청 마다 새로운 인스턴스를 생성되고 요청이 완료되면 파기된다
// proxyMode는 가짜 프록시 클래스를 만들어두고 다른 빈에 미리 주입해 둔다
// Provider를 사용하든 프록시를 사용하든 핵심 아이디어는 진짜 객체 조회를 꼭 필요한 시점까지 지연처리 한다는 점이다
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "] " + "[" + requestURL +"] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create "+ this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close "+ this);
    }
}
