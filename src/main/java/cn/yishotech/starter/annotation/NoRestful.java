/**
 * 项目名称:  restful-spring-boot-starter
 * 公司名称:  YiShoTech
 * All rights Reserved, Designed By YiShoTech 2023-2024
 */
package cn.yishotech.starter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>类路径:cn.yishotech.starter.annotation.NoRestful</p>
 * <p>类描述:no use restful style</p>
 * <p>创建人:jason zong</p>
 * <p>创建时间:2024/09/29 17:13</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NoRestful {
}
