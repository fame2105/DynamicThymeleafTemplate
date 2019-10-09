package com.learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.dialect.SpringStandardDialect;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@Configuration
@EnableWebMvc
public class ThymeleafConfig implements WebMvcConfigurer{

    @Bean(name = "templateEngine")
    public SpringTemplateEngine getTemplateEngine() {
        SpringStandardDialect dialect = new SpringStandardDialect();
        dialect.setEnableSpringELCompiler(true);
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(getClassLoaderTemplateResolver());
        templateEngine.addTemplateResolver(getStringTemplateResolver());
        templateEngine.setDialect(dialect);
        return templateEngine;
    }

    public ClassLoaderTemplateResolver getClassLoaderTemplateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setOrder(1);
        templateResolver.setCheckExistence(true);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    @Bean(name = "templateResolver")
    public StringTemplateResolver getStringTemplateResolver() {
        StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setOrder(2);
        return templateResolver;
    }

}
