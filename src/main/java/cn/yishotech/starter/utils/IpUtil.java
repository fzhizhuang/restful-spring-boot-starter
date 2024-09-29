/**
 * 项目名称:  restful-spring-boot-starter
 * 公司名称:  YiShoTech
 * All rights Reserved, Designed By YiShoTech 2023-2024
 */
package cn.yishotech.starter.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.yishotech.starter.config.RestfulProperties;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.File;
import java.util.Objects;

/**
 * <p>类路径:cn.yishotech.starter.utils.IpUtil</p>
 * <p>类描述:Ip工具类</p>
 * <p>创建人:jason zong</p>
 * <p>创建时间:2024/09/30 00:38</p>
 */
@Slf4j
public class IpUtil {

    private static final String UNKNOWN = "unknown";


    private static final RestfulProperties.Ip2regionProperties ip2regionProperties = SpringUtil.getBean(RestfulProperties.Ip2regionProperties.class);


    /**
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * @return 请求头信息
     */
    public static String getDevice() {
        HttpServletRequest request = getRequest();
        String uaStr = request.getHeader("User-Agent");
        UserAgent ua = UserAgentUtil.parse(uaStr);
        return uaStr;
    }

    /**
     * @return 请求头信息
     */
    public static String getDeviceBrowser() {
        HttpServletRequest request = getRequest();
        String uaStr = request.getHeader("User-Agent");
        UserAgent ua = UserAgentUtil.parse(uaStr);
        return ua.getBrowser().toString();
    }

    /**
     * @return 请求头信息
     */
    public static String getDeviceSystem() {
        HttpServletRequest request = getRequest();
        String uaStr = request.getHeader("User-Agent");
        UserAgent ua = UserAgentUtil.parse(uaStr);
        return ua.getPlatform().toString();
    }

    public static String getIpAddr() {
        String ip = null;
        HttpServletRequest request = getRequest();
        String ipAddresses = request.getHeader("X-Real-IP");
        if (Objects.isNull(ipAddresses) || ipAddresses.isEmpty() || "unknown".equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("X-Forwarded-For");
        }
        if (Objects.isNull(ipAddresses) || ipAddresses.isEmpty() || "unknown".equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (Objects.isNull(ipAddresses) || ipAddresses.isEmpty() || "unknown".equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (Objects.isNull(ipAddresses) || ipAddresses.isEmpty() || "unknown".equalsIgnoreCase(ipAddresses)) {
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (Objects.nonNull(ipAddresses) && !ipAddresses.isEmpty()) {
            ip = ipAddresses.split(",")[0];
        }
        if (Objects.isNull(ip) || ip.isEmpty() || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * 根据ip获取城市地理位置信息
     * 中国|0|江苏省|宿迁市|移动
     *
     * @param ip IP
     * @return 城市地理位置信息
     */
    public static String getIpToCityInfo(String ip) {
        try {

            ResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(ip2regionProperties.getLocation());
            String dbPath = "";
            if (resource.getURI().getScheme().equals("jar")) {

                File file = new File("src/main/resources/" + ((ClassPathResource) resource).getPath());
                FileUtil.writeFromStream(resource.getInputStream(), file);
                dbPath = file.getAbsolutePath();
            } else {
                dbPath = resource.getFile().getPath();
            }


            File file = new File(dbPath);
            //如果当前文件不存在，则从缓存中复制一份
            if (!file.exists()) {
                log.error("ip2region.xdb文件找不到请填写类路径");
                return "UNKNOWN";
            }
            //创建查询对象
            Searcher searcher = Searcher.newWithFileOnly(dbPath);
            //开始查询
            return searcher.search(ip);
        } catch (Exception e) {
            log.error("Ip查询城市地址解析失败{}", e.getMessage());
        }
        //默认返回空字符串
        return "UNKNOWN";
    }

    /**
     * 获取城市信息 中国·江苏省·宿迁市
     *
     * @param ip IP
     * @return 城市信息
     */
    public static String getCityInfo(String ip) {
        String ipToCityInfo = getIpToCityInfo(ip);
        // 解析
        if (StringUtils.isBlank(ipToCityInfo)) return UNKNOWN;
        String[] split = ipToCityInfo.split("\\|");
        String country = split[0];
        String province = split[2];
        String city = split[3];
        return String.format("%s·%s·%s", country, province, city);
    }


    /**
     * 获取城市信息 江苏·宿迁
     *
     * @param ip IP
     * @return 城市信息
     */
    public static String getCity(String ip) {
        String cityInfo = getCityInfo(ip);
        if (StringUtils.isBlank(cityInfo)) return UNKNOWN;
        String[] split = cityInfo.split("·");
        String province = split[1];
        String city = split[2];
        if (province.contains("省")) {
            province = province.replace("省", "");
        }
        if (city.contains("市")) {
            city = city.replace("市", "");
        }
        return String.format("%s·%s", province, city);
    }
}
