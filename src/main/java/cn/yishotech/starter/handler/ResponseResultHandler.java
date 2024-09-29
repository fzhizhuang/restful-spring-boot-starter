/**
 * 项目名称:  restful-spring-boot-starter
 * 公司名称:  YiShoTech
 * All rights Reserved, Designed By YiShoTech 2023-2024
 */
package cn.yishotech.starter.handler;

import cn.yishotech.starter.annotation.NoRestful;
import cn.yishotech.starter.resp.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * <p>类路径:cn.yishotech.starter.handler.ResponseResultHandler</p>
 * <p>类描述:响应处理器</p>
 * <p>创建人:jason zong</p>
 * <p>创建时间:2024/09/29 17:16</p>
 */
@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    /**
     * 判断是否需要执行beforeBodyWrite方法，true执行.
     * 通过该方法可以选择哪些类或者哪些方法的response要进行处理，其他的不进行处理.
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 方法没有INoRestful注解，且response不是R类型时启用beforeBodyWrite
        return !returnType.hasMethodAnnotation(NoRestful.class) && !returnType.getParameterType().isAssignableFrom(R.class);
    }

    @Override
    @SneakyThrows
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof R<?>) {
            return body;
        } else if (Objects.isNull(body)) {
            return R.success();
        } else if (body instanceof String) {
            return new ObjectMapper().writeValueAsString(R.success(body));
        } else {
            return R.success(body);
        }
    }
}
