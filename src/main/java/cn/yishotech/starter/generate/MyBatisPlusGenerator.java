/**
 * 项目名称:  restful-spring-boot-starter
 * 公司名称:  YiShoTech
 * All rights Reserved, Designed By YiShoTech 2023-2024
 */
package cn.yishotech.starter.generate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * <p>类路径:cn.yishotech.starter.generate.MybatisPlusGenerator</p>
 * <p>类描述:MybatisPlus代码生成工具</p>
 * <p>创建人:jason zong</p>
 * <p>创建时间:2024/10/03 22:34</p>
 */
public class MyBatisPlusGenerator {

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

    private static String getUserInput(String prompt, Scanner input) {
        String value;
        do {
            System.out.println(prompt);
            value = input.nextLine();
            if (value.isEmpty()) {
                System.out.println("该项不能为空，请重新输入。");
            }
        } while (value.isEmpty());
        return value;
    }

    public static void generateTable() {
        Scanner input = new Scanner(System.in);
        String host = getUserInput("【请输入数据库主机:端口】, eg:127.0.0.1:3306", input);
        String username = getUserInput("【请输入用户名】", input);
        String password = getUserInput("【请输入密码】", input);
        String database = getUserInput("【请输入数据库名】", input);

        String url = "jdbc:mysql://" + host + "/" + database + "?serverTimezone=GMT%2B8";
        try {
            FastAutoGenerator.create(url, username, password)
                    // 全局配置
                    .globalConfig((scanner, builder) -> {
                        // 设置注释信息-作者
                        builder.author(scanner.apply("【请输入作者名称】"));
                        // 设置代码生成的路径
                        builder.outputDir(System.getProperty("user.dir") + "/src/main/java");
                    })
                    // 包配置
                    .packageConfig((scanner, builder) -> {
                        builder.parent(scanner.apply("【请输入包名】"))
                                // 设置 xml 文件路径
                                .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/src/main/resources/mapper"));
                    })
                    // 策略配置
                    .strategyConfig((scanner, builder) -> {
                        builder.addInclude(getTables(scanner.apply("【请输入表名，多个英文逗号分隔？所有输入 all】")))
                                .controllerBuilder().enableRestStyle().enableHyphenStyle().build();
                        builder.serviceBuilder()
                                .formatServiceFileName("%sService")
                                .formatServiceImplFileName("%sServiceImp")
                                .build();
                        builder.mapperBuilder()
                                .enableBaseResultMap()
                                .enableBaseColumnList();
                        // entity 的策略配置
                        builder.entityBuilder()
                                // 启用 Lombok 插件
                                .enableLombok()
                                .entityBuilder()
                                .enableTableFieldAnnotation()
                                .versionColumnName("version")
                                .logicDeleteColumnName("is_delete")
                                // 设置字段名的命名策略为下划线转驼峰命名
                                .columnNaming(NamingStrategy.underline_to_camel)
                                // 主键策略递增
                                .idType(IdType.AUTO)
                                .formatFileName("%sEntity")
                                .build();
                    })
                    .execute();
        } catch (Exception e) {
            System.out.println("数据库连接或代码生成过程中出现错误：" + e.getMessage());
        }
    }
}