package com.cmos.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @FileName RocketMQClient.java
 * @Description:TODO
 * @author JackHisen(gu.weidong)
 * @version V1.0
 * @createtime 2018年3月22日 下午6:58:24
 * 修改历史：
 * 时间           作者          版本        描述
 *====================================================
 *
 */
@Service
public class RocketMQConsumer {
    /**
     * 消费者的组名
     */
    @Value("${apache.rocketmq.consumer.PushConsumer}")
    private String consumerGroup;

    /**
     * NameServer 地址
     */
    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;

    /**
     * @PostConstruct 是spring框架的注解，在方法上加该注解会
     * 在项目启动的时候执行该方法，也可以理解为在spring容器初始
     * 化的时候执行该方法。
     */
    public void defaultMQPushConsumer() {
        //消费者的组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);

        //指定NameServer地址，多个地址以 ; 隔开
        consumer.setNamesrvAddr(namesrvAddr);
        try {
            //订阅PushTopic下Tag为push的消息
            consumer.subscribe("TopicTest", "push");

            //设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
            //如果非第一次启动，那么按照上次消费的位置继续消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener((MessageListenerConcurrently) (list, context) -> {
                //->为Java8的lambda表达式,就是匿名函数,具体可以参考该文章https:segmentfault.com/q/1010000007518474。
                try {
                    for (MessageExt messageExt : list) {
                        //输出消息内容
                        System.out.println("messageExt: " + messageExt);
                        String messageBody = new String(messageExt.getBody());
                        //输出消息内容
                        System.out.println("消费响应：msgId : " + messageExt.getMsgId() + ",  msgBody : " + messageBody);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //稍后再试
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                //消费成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


