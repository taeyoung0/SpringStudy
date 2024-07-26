package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        // 애플리케이션 컨텍스트를 생성하고 ComponentFilterAppConfig 설정을 사용
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        // BeanA를 컨텍스트에서 가져와서 존재 여부를 확인
        BeanA bean = ac.getBean(BeanA.class);
        assertThat(bean).isNotNull();       // BeanA가 컨텍스트에 존재하는지 확인

        // BeanB를 컨텍스트에서 가져올 때 NoSuchBeanDefinitionException이 발생하는지 확인
        assertThrows(NoSuchBeanDefinitionException.class,
                () ->ac.getBean("BeanB", BeanB.class)); // BeanB가 컨텍스트에 존재하지 않는지 확인
    }

    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class), // MyIncludeComponent 애노테이션이 붙은 클래스만 포함
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)  // MyExcludeComponent 애노테이션이 붙은 클래스는 제외
    )
    static class ComponentFilterAppConfig {

    }

}
