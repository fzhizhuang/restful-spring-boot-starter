/**
 * 项目名称:  restful-spring-boot-starter
 * 公司名称:  YiShoTech
 * All rights Reserved, Designed By YiShoTech 2023-2024
 */
package cn.yishotech.starter.annotation;

import cn.yishotech.starter.config.RestfulAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>类路径:cn.yishotech.starter.annotation.EnableRestful</p>
 * <p>类描述:开启Restful</p>
 * <p>创建人:jason zong</p>
 * <p>创建时间:2024/09/29 19:48</p>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RestfulAutoConfiguration.class})
@Documented
public @interface EnableRestful {
}
