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

        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient(); // 스프링 빈 라이프 사이클: 객체 생성 -> 의존 관계 주입
            networkClient.setUrl("http://hello-spring.dev");    // 객체의 생성과 초기화를 분리하자
            return networkClient;
        }
    }
}
