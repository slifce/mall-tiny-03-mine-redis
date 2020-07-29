package com.macro.mall.tiny.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaMailSender {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    public void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();//简单邮件发送
        message.setSubject("今晚开会");
        message.setText("------linglingling today is funny day!!!!!");
        message.setTo("18823786682@163.com");
        message.setFrom("734507707@qq.com");
        mailSender.send(message);
    }

    @Test
    public void test02() throws Exception{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);//带附件邮件发送
        helper.setSubject("今晚开会");
        helper.setText("<b style='color:red;'>------linglingling today is funny day!!!!!</b>",true);
        helper.addAttachment("Beauty.jpg",
                new File("C:\\Users\\Administrator\\Desktop\\pictureContext\\02997E32C9FCFC112D3368FC9F2022D1.jpg"));
        helper.addAttachment("smallPic.jpg",
                new File("C:\\Users\\Administrator\\Desktop\\pictureContext\\pic.jpg"));
        helper.setTo("18823786682@163.com");
        helper.setFrom("734507707@qq.com");
        mailSender.send(message);
    }

}
