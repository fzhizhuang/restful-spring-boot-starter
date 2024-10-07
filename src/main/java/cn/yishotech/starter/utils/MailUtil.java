/**
 * 项目名称:  email-spring-boot-starter
 * 公司名称:  YiShoTech
 * All rights Reserved, Designed By YiShoTech 2023-2024
 */
package cn.yishotech.starter.utils;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.Map;

/**
 * <p>类路径:cn.yishotech.starter.email.util.MailUtil</p>
 * <p>类描述:邮箱工具类</p>
 * <p>创建人:jason zong</p>
 * <p>创建时间:2024/10/07 18:12</p>
 */
@Slf4j
public class MailUtil {

    @Resource
    private JavaMailSender mailSender;
    @Resource
    private TemplateEngine templateEngine;
    @Resource
    private MailProperties properties;

    /**
     * 发送文本邮件
     *
     * @param to      邮箱
     * @param subject 主题
     * @param content 内容
     */
    public void sendEmail(String to, String subject, String content) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setFrom(properties.getUsername());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);
            helper.setSentDate(new Date());
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("send email error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 发送Html邮件
     *
     * @param to       邮箱
     * @param subject  主题
     * @param params   参数
     * @param template 邮件模板
     */
    public void sendHtmlEmail(String to, String subject, Map<String, Object> params, String template) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setFrom(properties.getUsername());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setSentDate(new Date());
            // 配置html内容
            Context context = new Context();
            context.setVariables(params);
            String content = templateEngine.process(template, context);
            helper.setText(content, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("send email error", e);
            throw new RuntimeException(e);
        }
    }
}
