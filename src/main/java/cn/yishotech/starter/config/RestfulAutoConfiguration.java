/**
 * 项目名称:  restful-spring-boot-starter
 * 公司名称:  YiShoTech
 * All rights Reserved, Designed By YiShoTech 2023-2024
 */
package cn.yishotech.starter.config;

import cn.yishotech.starter.handler.GlobalExceptionHandler;
import cn.yishotech.starter.handler.ResponseResultHandler;
import cn.yishotech.starter.utils.MailUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;

/**
 * <p>类路径:cn.yishotech.starter.config.RestfulAutoConfiguration</p>
 * <p>类描述:Restful自动配置</p>
 * <p>创建人:jason zong</p>
 * <p>创建时间:2024/09/29 17:06</p>
 */
@Configuration
public class RestfulAutoConfiguration {

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    public ResponseResultHandler responseResultHandler() {
        return new ResponseResultHandler();
    }

    @Bean
    public FaviconConfiguration faviconConfiguration() {
        return new FaviconConfiguration();
    }

    @Bean
    public RestfulProperties restfulProperties() {
        return new RestfulProperties();
    }

    @Bean
    public RestfulProperties.Ip2regionProperties ip2regionProperties() {
        return new RestfulProperties.Ip2regionProperties();
    }

    @Bean
    public JacksonConfiguration jacksonConfiguration() {
        return new JacksonConfiguration();
    }

    @Bean
    @ConditionalOnProperty(prefix = "restful.mail", value = "enabled", havingValue = "true")
    public MailUtil mailUtil() {
        return new MailUtil();
    }

    @Bean
    public TemplateEngine templateEngine() {
        return new TemplateEngine();
    }

    @Bean
    @ConditionalOnProperty(prefix = "restful.mail", value = "enabled", havingValue = "true")
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }

    @Bean
    public RestfulProperties.MailAutoProperties mailAutoProperties() {
        return new RestfulProperties.MailAutoProperties();
    }

    @Bean
    public MailProperties mailProperties() {
        return new MailProperties();
    }


}
