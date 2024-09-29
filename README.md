<h3 align="center">SpringBoot Restful 通用模块</h3>

## 介绍

### 使用

#### pom引入依赖

```xml
<dependency>
    <groupId>cn.yishotech</groupId>
    <artifactId>restful-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### 配置
在启动类上使用@EnableRestful注解，导入配置
```java
@EnableRestful
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
```

如果需要自定义其他异常，可按照如下方式实现
```java
@Setter
@Getter
public class CacheException extends RuntimeException {

    private Integer code;
    private String msg;

    public CacheException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
```
异常处理
```java
@RestControllerAdvice
public class ExceptionHandler extends GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(CacheException.class)
    public R<Void> cacheException(CacheException e) {
        log.info("缓存异常:{}", e.getMsg());
        return R.fail(e.getCode(), e.getMsg());
    }
}

```

```java
@RestController
@RequestMapping
public class DemoController {

    /**
     * {
     "success": true,
     "code": 200,
     "message": "success",
     "data": "hello world"
     }
     *
     */
    @GetMapping("/get")
    public String get() {
        return "success";
    }

    /* hello world */
    @NoRestful
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
```

## 许可证

根据 License 许可证分发。打开 [LICENSE](LICENSE) 查看更多内容。
