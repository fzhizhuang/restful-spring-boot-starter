/**
 * 项目名称:  restful-spring-boot-starter
 * 公司名称:  YiShoTech
 * All rights Reserved, Designed By YiShoTech 2023-2024
 */
package cn.yishotech.starter.exception;

/**
 * <p>类路径:cn.yishotech.starter.exception.IError</p>
 * <p>类描述:错误码枚举接口</p>
 * <p>创建人:jason zong</p>
 * <p>创建时间:2024/10/08 01:28</p>
 */
public interface IError {

    Integer code();

    String message();
}
