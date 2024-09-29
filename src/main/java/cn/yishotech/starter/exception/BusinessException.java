package cn.yishotech.starter.exception;

import cn.yishotech.starter.resp.HttpCode;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>类路径:cn.yishotech.starter.exception.BusinessException</p>
 * <p>类描述:业务异常</p>
 * <p>创建人:jason zong</p>
 * <p>创建时间:2024/09/29 16:54</p>
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {

    private Integer code;
    private String message;

    public BusinessException(String message) {
        this(HttpCode.BUSINESS_EXCEPTION, message);
    }

    public BusinessException(HttpCode httpCode, String message) {
        super(message);
        this.code = httpCode.getCode();
        this.message = message;
    }

}
