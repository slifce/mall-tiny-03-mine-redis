package com.macro.mall.tiny.spring;

import com.macro.mall.tiny.dto.ReginPerson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2020/8/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSpring {

    private ApplicationContext ctx = null;

    @Test
    public void reginCode(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-test.xml");
        Object ctxBean = ctx.getBean("reginPerson");
        Assert.assertTrue(ctxBean instanceof ReginPerson);
    }
}
