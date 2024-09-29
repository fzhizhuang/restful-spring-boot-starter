/**
 * 项目名称:  restful-spring-boot-starter
 * 公司名称:  YiShoTech
 * All rights Reserved, Designed By YiShoTech 2023-2024
 */
package cn.yishotech.starter.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>类路径:cn.yishotech.starter.config.FaviconConfiguration</p>
 * <p>类描述:Favicon配置，忽略favicon.ico请求，防止浏览器访问时出现异常干扰日志</p>
 * <p>创建人:jason zong</p>
 * <p>创建时间:2024/09/29 19:43</p>
 */
@Configuration
public class FaviconConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加一个拦截器，拦截所有请求，忽略favicon.ico请求
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
                if (!"GET".equalsIgnoreCase(request.getMethod()) || !request.getRequestURI().equals("/favicon.ico")) {
                    return true;
                }
                response.setStatus(HttpStatus.NO_CONTENT.value()); // 设置状态码为204 No Content
                return false;
            }
        }).addPathPatterns("/**");
    }
}
