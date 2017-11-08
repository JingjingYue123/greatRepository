package com.djcps.crm.order.mq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Lancher on 2017/8/21.
 */
@Service
public class CrmProducer {
    @Resource
    private AmqpTemplate amqpTemplate;

    @Transactional("rabbitTxManage")
    public void sendMsg(String exchange,String routingKey,Object message,String ftest) throws InterruptedException {
        amqpTemplate.convertAndSend(exchange, routingKey, message);
    }

    @Transactional("rabbitTxManage")
    public void sendBalanceUpdateMsg(String message) throws InterruptedException{
        amqpTemplate.convertAndSend("exchangeCrm",
                "queueUpdateIntegralCrmKey", message);
    }

}
