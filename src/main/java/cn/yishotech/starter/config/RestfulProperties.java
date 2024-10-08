/**
 * 项目名称:  restful-spring-boot-starter
 * 公司名称:  YiShoTech
 * All rights Reserved, Designed By YiShoTech 2023-2024
 */
package cn.yishotech.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>类路径:cn.yishotech.starter.config.RestfulProperties</p>
 * <p>类描述:Restful配置参数</p>
 * <p>创建人:jason zong</p>
 * <p>创建时间:2024/09/30 00:40</p>
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "restful")
public class RestfulProperties {

    @Data
    @Configuration
    @ConfigurationProperties(prefix = "restful.ip2region")
    public static class Ip2regionProperties {
        /**
         * 是否使用外部的IP数据文件.
         */
        private boolean external = false;
        /**
         * ip2region.db 文件路径，默认： classpath:ip2region/ip2region.db
         */
        private String location = "classpath:ip2region/ip2region.xdb";
    }

}
