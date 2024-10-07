/**
 * 项目名称:  restful-spring-boot-starter
 * 公司名称:  YiShoTech
 * All rights Reserved, Designed By YiShoTech 2023-2024
 */
package cn.yishotech.starter.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>类路径:cn.yishotech.starter.utils.IdGenerator</p>
 * <p>类描述:雪花Id生成工具类</p>
 * <p>创建人:jason zong</p>
 * <p>创建时间:2024/10/08 00:41</p>
 */
@Slf4j
public class IdGenerator {

    /**
     * 第几号机器
     */
    private static final Long dataCenterId = 1L;

    private static Snowflake snowflake;

    static {
        Long workerId;
        try {
            // 获取本机的IP地址编码
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr()) % 31;
            log.info("当前机器的workerId:{}", workerId);
            snowflake = IdUtil.getSnowflake(workerId, dataCenterId);
        } catch (Exception e) {
            log.warn("当前机器的workerId获取失败", e);
            workerId = (long) NetUtil.getLocalhostStr().hashCode();
            snowflake = IdUtil.getSnowflake(workerId, dataCenterId);
        }
    }

    /**
     * 生成Id
     *
     * @return id
     */
    public static Long nextId() {
        return snowflake.nextId();
    }

    /**
     * 生成String类型id
     *
     * @return id
     */
    public static String nextIdStr() {
        return snowflake.nextIdStr();
    }

}
