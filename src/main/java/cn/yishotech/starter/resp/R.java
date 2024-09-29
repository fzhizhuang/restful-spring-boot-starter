/**
 * 项目名称:  restful-spring-boot-starter
 * 公司名称:  YiShoTech
 * All rights Reserved, Designed By YiShoTech 2023-2024
 */
package cn.yishotech.starter.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>类路径:cn.yishotech.starter.resp.R</p>
 * <p>类描述:统一响应结果处理</p>
 * <p>创建人:jason zong</p>
 * <p>创建时间:2024/09/29 16:47</p>
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"success", "code", "message", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "返回响应", defaultValue = "统一响应结果处理")
public class R<T> implements Serializable {

    @Schema(description = "操作结果", example = "true")
    private Boolean success;

    @Schema(description = "响应码", example = "200")
    private Integer code;

    @Schema(description = "响应信息", example = "success")
    private String message;

    @Schema(description = "响应数据")
    private T data;


    /**
     * 构建成功响应
     *
     * @param data 数据
     * @param <T>  数据泛型
     * @return 响应数据
     */
    public static <T> R<T> success(T data) {
        return build(Boolean.TRUE, HttpCode.SUCCESS, data);
    }

    /**
     * 构建成功响应
     *
     * @param <T> 数据泛型
     * @return 响应数据
     */
    public static <T> R<T> success() {
        return build(Boolean.TRUE, HttpCode.SUCCESS, null);
    }

    /**
     * 构建错误响应
     *
     * @param httpCode 响应枚举
     * @param <T>      数据泛型
     * @return 响应数据
     */
    public static <T> R<T> fail(HttpCode httpCode) {
        return fail(httpCode.getCode(), httpCode.getMessage());
    }

    /**
     * 构建错误响应
     *
     * @param httpCode 响应枚举
     * @param message  响应消息
     * @param <T>      数据泛型
     * @return 响应数据
     */
    public static <T> R<T> fail(HttpCode httpCode, String message) {
        return fail(httpCode.getCode(), message);
    }

    public static <T> R<T> fail(Integer code, String message) {
        return build(Boolean.FALSE, code, message, null);
    }


    /**
     * 构建返回响应
     *
     * @param success  操作结果
     * @param httpCode 响应枚举
     * @param data     数据
     * @param <T>      数据泛型
     * @return 响应数据
     */
    public static <T> R<T> build(Boolean success, HttpCode httpCode, T data) {
        return build(success, httpCode.getCode(), httpCode.getMessage(), data);
    }

    /**
     * 构建返回响应
     *
     * @param success 操作结果
     * @param code    响应码
     * @param message 响应信息
     * @param data    数据
     * @param <T>     数据泛型
     * @return 响应数据
     */
    public static <T> R<T> build(Boolean success, Integer code, String message, T data) {
        return new R<>(success, code, message, data);
    }
}

