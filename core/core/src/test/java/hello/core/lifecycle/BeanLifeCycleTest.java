package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        // ConfigurableApplicationContext는 애플리케이션의 라이프사이클을 관리할 수 있는 기능을 제공
        // 자식은 부모를 참조할 수 있다
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean(initMethod = "init", destroyMethod = "close") // 설정 정보에 초기화, 소멸 메서드 지정, destroyMethod 는 기본값이 (inferred) (추론)으로 등록
        public NetworkClient networkClient() {              // 이 추론 기능은 close shutdown이라는 이름의 메서드를 자동으로 호출해줌
            NetworkClient networkClient = new NetworkClient(); // 스프링 빈 라이프 사이클: 객체 생성 -> 의존 관계 주입
            networkClient.setUrl("http://hello-spring.dev");    // 객체의 생성과 초기화를 분리하자
            return networkClient;
        }
    }
}
