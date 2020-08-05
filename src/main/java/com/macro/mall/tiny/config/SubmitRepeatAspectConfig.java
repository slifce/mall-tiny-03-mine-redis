package com.macro.mall.tiny.config;

import com.macro.mall.tiny.annotation.CheckSubmitRepeat;
import com.macro.mall.tiny.exception.CheckSubmitRepeatException;
import com.macro.mall.tiny.service.RedisService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @Author lyp
 * @Date 2020/8/5 11:11
 * @Description 防重复提交验证
 */
@SuppressWarnings("unchecked")
@Aspect
@Component
public class SubmitRepeatAspectConfig {

    @Autowired
    private RedisService redisService;


    @Pointcut("@annotation(com.macro.mall.tiny.annotation.CheckSubmitRepeat)")
    public void pointCut() {
    }

    @Before(value = "@within(com.macro.mall.tiny.annotation.CheckSubmitRepeat) || @annotation(com.macro.mall.tiny.annotation.CheckSubmitRepeat)")
    public void before(JoinPoint jp){
    }

    @After(value = "@within(com.macro.mall.tiny.annotation.CheckSubmitRepeat) || @annotation(com.macro.mall.tiny.annotation.CheckSubmitRepeat)")
    public void after(JoinPoint joinPoint) throws Throwable {
    }

    @AfterReturning("pointCut()")
    public void afterMethod(){
    }

    @Around(value = "@within(checkSubmitRepeat) || " + "@annotation(checkSubmitRepeat)")
    public Object interceptor(ProceedingJoinPoint pjp, CheckSubmitRepeat checkSubmitRepeat) throws Exception {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CheckSubmitRepeat localLock = method.getAnnotation(CheckSubmitRepeat.class);
        String key = getKey(method.getName() + ":" , "123456");
        if (!StringUtils.isEmpty(key)) {
            if (redisService.get(key) != null) {
                throw new CheckSubmitRepeatException(localLock.msg());
            }
            // 如果是第一次请求,就将 key 当前对象压入缓存中
            redisService.set(key, key);
            redisService.expire(key, 60);
        }

        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throw new Exception(throwable.getMessage());
        } finally {

        }
    }

    /**
     * key 的生成策略,如果想灵活可以写成接口与实现类的方式（TODO 后续讲解）
     *
     * @param keyExpress 表达式
     * @param sessionId  会话id
     * @return 生成的key
     */
    private String getKey(String keyExpress, String sessionId) {
        return keyExpress + sessionId;
    }
}
