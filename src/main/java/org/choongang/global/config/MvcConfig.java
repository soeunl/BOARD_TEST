package org.choongang.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaAuditing // 엔티티의 생성일자(createdDate)와 수정일자(lastModifiedDate)를 자동으로 관리할 수 있도록 도와주는 기능을 활성화
public class MvcConfig implements WebMvcConfigurer {
    @Bean // 빈으로 꼭 등록해야지 활성화 됩니다!
    public HiddenHttpMethodFilter httpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}
