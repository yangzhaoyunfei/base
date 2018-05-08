package com.example.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Description: 所有 PO 的基类,实现了 Serializable 接口,以便 JVM 可以序列化 PO 实例,为通过流发送,缓存,集群等功能提供便利.
 *
 * @author yangzhaoyunfei yangzhaoyunfei@qq.com
 * @date 2018/4/20
 */
public class BaseDomain implements Serializable {
    /**
     * 统一提供 toString() 方法,以方便打印 PO 实例
     *
     * @return
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}