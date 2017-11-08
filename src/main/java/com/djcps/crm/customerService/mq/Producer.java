package com.djcps.crm.customerService.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class Producer {
    @Resource
    private AmqpTemplate amqpTemplate;

    @Transactional("rabbitTxManage")
    public void sendMsg(String exchange,String routingKey,Object message) throws InterruptedException {
        amqpTemplate.convertAndSend(exchange, routingKey, message);
    }

    @Transactional("rabbitTxManage")
    public void sendBalanceUpdateMsg(String message) throws InterruptedException{
        amqpTemplate.convertAndSend("exchangeOrder",
                "queueUpdateOrderServerKey", message);
    }

}
