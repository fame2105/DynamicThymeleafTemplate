package com.learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.dialect.SpringStandardDialect;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;


@Configuration
@EnableWebMvc
public class ThymeleafConfig implements WebMvcConfigurer {
    @Bean(name ="templateResolver")
    public StringTemplateResolver getTemplateResolver() {
        StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.TEXT);
        return templateResolver;
    }

    @Bean(name ="templateEngine")
    public SpringTemplateEngine getTemplateEngine() {
        SpringStandardDialect dialect = new SpringStandardDialect();
        dialect.setEnableSpringELCompiler(true);
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(getTemplateResolver());
        templateEngine.setDialect(dialect);
        return templateEngine;
    }
}
