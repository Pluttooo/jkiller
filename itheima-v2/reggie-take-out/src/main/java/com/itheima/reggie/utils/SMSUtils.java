package com.itheima.reggie.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: daonian
 * @date: 2023/01/08 22:50
 */
@Component
public class SMSUtils {

    // 将需要使用的service注入
    @Autowired
    private JavaMailSender javaMailSender;

    // 定义静态变量来接收 类型要一致
    private static JavaMailSender mailSender;

    // 通过@PostConstruct注解来将容器中的bean赋值给静态server
    @PostConstruct
    public void init() {
        mailSender = javaMailSender;
    }

    /**
     * 发送邮件
     * @param subject
     * @param text
     * @param mailReceiver
     */
    public static void sendMessage(String subject, String text, String mailReceiver) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // mailMessage.setFrom(mailSendMan);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        mailMessage.setTo(mailReceiver);
        try {
            mailSender.send(mailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
