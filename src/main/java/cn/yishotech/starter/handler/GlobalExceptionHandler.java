/**
 * 项目名称:  restful-spring-boot-starter
 * 公司名称:  YiShoTech
 * All rights Reserved, Designed By YiShoTech 2023-2024
 */
package cn.yishotech.starter.handler;

import cn.yishotech.starter.exception.BusinessException;
import cn.yishotech.starter.resp.HttpCode;
import cn.yishotech.starter.resp.R;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>类路径:cn.yishotech.starter.handler.GlobalExceptionHandler</p>
 * <p>类描述:全局异常处理器</p>
 * <p>创建人:jason zong</p>
 * <p>创建时间:2024/09/29 16:57</p>
 */
@RestControllerAdvice
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 兜底异常
     *
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(Exception.class)
    public R<Void> error(Exception e) {
        log.error("exception:{}", e.getMessage(), e);
        return R.fail(HttpCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 参数格式异常处理
     *
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public R<Void> badRequestException(IllegalArgumentException e) {
        log.error("参数格式不合法：{}", e.getMessage(), e);
        String errorMsg = String.format("%s:%s", HttpCode.INVALID_ARGUMENT.getMessage(), e.getMessage());
        return R.fail(HttpCode.INVALID_ARGUMENT, errorMsg);
    }

    /**
     * 权限不足异常处理
     *
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler({AccessDeniedException.class})
    public R<Void> badRequestException(AccessDeniedException e) {
        String errorMsg = String.format("%s:%s", HttpCode.FORBIDDEN.getMessage(), e.getMessage());
        return R.fail(HttpCode.FORBIDDEN, errorMsg);
    }

    /**
     * 参数缺失异常处理
     *
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public R<Void> badRequestException(Exception e) {
        String errorMsg = String.format("%s:%s", HttpCode.INVALID_ARGUMENT.getMessage(), e.getMessage());
        return R.fail(HttpCode.INVALID_ARGUMENT, errorMsg);
    }

    /**
     * 空指针异常
     *
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(NullPointerException.class)
    public R<Void> handleTypeMismatchException(NullPointerException e) {
        log.error("空指针异常，{}", e.getMessage(), e);
        String errorMsg = String.format("%s:%s", HttpCode.INTERNAL_SERVER_ERROR.getMessage(), e.getMessage());
        return R.fail(HttpCode.INTERNAL_SERVER_ERROR, errorMsg);
    }

    /**
     * 业务异常处理
     *
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(BusinessException.class)
    public R<Void> error(BusinessException e) {
        return R.fail(e.getCode(), e.getMessage());
    }

    /**
     * 验证对象类型参数
     *
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(BindException.class)
    public R<Void> BindExceptionHandler(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream().map(o -> o.getField() + o.getDefaultMessage()).collect(Collectors.toList());
        String errorMsg = String.format("%s:%s", HttpCode.INVALID_ARGUMENT.getMessage(), String.join("; ", collect));
        return R.fail(HttpCode.INVALID_ARGUMENT, errorMsg);
    }

    /**
     * 验证单个参数类型
     *
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public R<Void> ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<String> errorMessages = new ArrayList<>();
        for (ConstraintViolation<?> violation : violations) {
            Path propertyPath = violation.getPropertyPath();
            String fieldName = ((PathImpl) propertyPath).getLeafNode().getName();
            String errorMessage = String.format("%s %s", fieldName, violation.getMessage());
            errorMessages.add(errorMessage);
        }
        String errorMsg = String.format("%s:%s", HttpCode.INVALID_ARGUMENT.getMessage(), String.join("; ", errorMessages));
        return R.fail(HttpCode.INVALID_ARGUMENT, errorMsg);
    }

    /**
     * 验证对象类型参数 JSON body 参数
     *
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<String> errorMessages = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        String errorMsg = String.format("%s:%s", HttpCode.INVALID_ARGUMENT.getMessage(), String.join("; ", errorMessages));
        return R.fail(HttpCode.INVALID_ARGUMENT, errorMsg);
    }


    /**
     * 接口不存在
     *
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public R<Void> error(NoHandlerFoundException e) {
        String errorMsg = String.format("%s:%s", HttpCode.NOT_FOUND.getMessage(), e.getRequestURL());
        return R.fail(HttpCode.NOT_FOUND, errorMsg);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public R<Void> handleNoResourceFoundException(NoResourceFoundException e) {
        String errorMsg = String.format("%s:%s", HttpCode.NOT_FOUND.getMessage(), e.getMessage());
        return R.fail(HttpCode.NOT_FOUND, errorMsg);
    }


    /**
     * 请求方法不被允许
     *
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<Void> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String errorMsg = String.format("%s:%s", HttpCode.METHOD_NOT_ALLOWED.getMessage(), e.getMessage());
        return R.fail(HttpCode.METHOD_NOT_ALLOWED, errorMsg);
    }

    /**
     * 请求与响应媒体类型不一致
     *
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public R<Void> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        String errorMsg = String.format("%s:%s", HttpCode.BAD_GATEWAY.getMessage(), e.getMessage());
        return R.fail(HttpCode.BAD_GATEWAY, errorMsg);
    }

    /**
     * body json参数解析异常
     *
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R<Void> HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String errorMsg = String.format("%s:%s", HttpCode.INVALID_ARGUMENT.getMessage(), e.getMessage());
        return R.fail(HttpCode.INVALID_ARGUMENT, errorMsg);
    }

}
