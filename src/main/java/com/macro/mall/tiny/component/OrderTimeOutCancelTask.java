package com.macro.mall.tiny.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 订单超时取消并解锁库存的定时器
 */
@Component
public class OrderTimeOutCancelTask {
    private Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);

    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 每10秒扫描一次，扫描设定超时时间之前下的订单，如果没支付则取消该订单
     * 0/10 * * * * *
     *
     * 每个时间点的第十秒种触发
     * 10 * * * * *
     *
     * 每10分钟扫描一次
     * * 0/10 * * * *
     *
     * ,	列出枚举值	在Minutes域使用5,10，表示在5分和10分各触发一次
     * -	表示触发范围	在Minutes域使用5-10，表示从5分到10分钟每分钟触发一次
     * *	匹配任意值	在Minutes域使用*, 表示每分钟都会触发一次
     * /	起始时间开始触发，每隔固定时间触发一次	在Minutes域使用5/10,表示5分时触发一次，每10分钟再触发一次
     * ?	在DayofMonth和DayofWeek中，用于匹配任意值	在DayofMonth域使用?,表示每天都触发一次
     * #	在DayofMonth中，确定第几个星期几	1#3表示第三个星期日
     * L	表示最后	在DayofWeek中使用5L,表示在最后一个星期四触发
     * W	表示有效工作日(周一到周五)	在DayofMonth使用5W，如果5日是星期六，则将在最近的工作日4日触发一次
     */
    @Scheduled(cron = "0/10 * * * * *")
    private void cancelTimeOutOrder() {
        // TODO: 此处应调用取消订单的方法，具体查看mall项目源码
        LOGGER.info("取消订单，并根据sku编号释放锁定库存");
    }
}
