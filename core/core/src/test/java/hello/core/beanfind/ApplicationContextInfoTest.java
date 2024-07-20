package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); // 애플리케이션 컨텍스트에 정의된 모든 빈의 이름을 가져옴
        for (String beanDefinitionName : beanDefinitionNames) { // 빈의 이름을 하나씩 출력
            Object bean = ac.getBean(beanDefinitionName);   // 각 빈의 이름을 사용하여 빈의 실제 객체를 가져옴, 빈에 매핑된 실제 객체(memberService의 실제 객체 MemberServiceImpl)
            System.out.println("name = " + beanDefinitionName + " object = " + bean ); // 빈의 이름과 객체를 출력
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) { // ROLE_APPLICATION은 내가 정의한 일반 애플리케이션 빈을 의미
                Object bean = ac.getBean(beanDefinitionName);                 // 이를 통해 스프링 내부에서 사용하는 빈과 구분해서 출력
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }

}
