package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);

        ClientBean prototypeBean1 = ac.getBean(ClientBean.class);
        int count1 = prototypeBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean prototypeBean2 = ac.getBean(ClientBean.class);
        int count2 = prototypeBean2.logic();
        assertThat(count2).isEqualTo(2);
        // 싱글톤 빈은 생성 시점에만 의존 관계를 주입받기 때문에 프로토타입 빈이 새로 생성되긴 하지만 싱글톤 빈과 계속 유지되는 것(프로토타입 의미 없어짐)
        // 여러 빈에서 같은 프로토타입 빈을 주입 받으면, 주입 받은 시점에 각각 새로운 프로토타입 빈이 생성된다
    }

    static class ClientBean {

        private final PrototypeBean prototypeBean;

        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
