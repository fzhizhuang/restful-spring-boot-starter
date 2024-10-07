/**
 * 项目名称:  restful-spring-boot-starter
 * 公司名称:  YiShoTech
 * All rights Reserved, Designed By YiShoTech 2023-2024
 */
package cn.yishotech.starter.resp;

import cn.yishotech.starter.exception.IError;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>类路径:cn.yishotech.starter.resp.HttpCode</p>
 * <p>类描述:响应结果枚举</p>
 * <p>创建人:jason zong</p>
 * <p>创建时间:2024/09/29 16:48</p>
 */
@Getter
@AllArgsConstructor
public enum HttpCode implements IError {

    SUCCESS(200, "success"),
    UNAUTHORIZED(401, "unauthorized"),
    UNAUTHORIZED_EXPIRED(402, "unauthorized expired"),
    FORBIDDEN(403, "forbidden"),
    NOT_FOUND(404, "resource not found"),
    METHOD_NOT_ALLOWED(405, "method not allowed"),
    REQUEST_TIMEOUT(406, "request timeout"),

    INTERNAL_SERVER_ERROR(500, "internal server error"),
    BAD_GATEWAY(502, "bad gateway"),

    INVALID_ARGUMENT(1000, "invalid argument"),

    BUSINESS_EXCEPTION(2000, "business exception"),
    ;


    private final Integer code;
    private final String message;

    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
