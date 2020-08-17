package com.macro.mall.tiny.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 消息体定义 实现Delayed接口就是实现两个方法即compareTo 和 getDelay最重要的就是getDelay方法，这个方法用来判断是否到期……
 * Created by Administrator on 2020/8/17.
 */
public class Message implements Delayed {

    private int id;

    private String body;//消息内容

    private long executeTime;//延迟时长；这个是必须属性，用于比较是否到了执行时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(long executeTime) {
        this.executeTime = executeTime;
    }

    public Message(int id, String body, long executeTime) {
        this.id = id;
        this.body = body;
        this.executeTime = executeTime;
    }

    // 延迟任务是否到时就是按照这个方法判断如果返回的是负数则说明到期否则还没到期
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.executeTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    // 自定义实现比较方法返回 1 0 -1三个参数
    @Override
    public int compareTo(Delayed o) {
        Message message = (Message) o;
        return Integer.valueOf(this.id) > Integer.valueOf(message.id) ? 1
                : (Integer.valueOf(this.id) < Integer.valueOf(message.id) ? -1 : 0);
    }
}
