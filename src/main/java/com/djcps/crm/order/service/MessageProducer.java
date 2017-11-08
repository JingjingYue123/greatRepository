package com.djcps.crm.order.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class MessageProducer {


	/*@Resource
    private AmqpTemplate amqpTemplate;
	
	@Transactional("rabbitTxManage")
	public void sendMsg(String exchange,String routingKey,Object message) throws InterruptedException {
//		System.out.println(1/0);
		amqpTemplate.convertAndSend(exchange, routingKey, message);
		System.out.println("1");
		amqpTemplate.convertSendAndReceive(exchange, routingKey, message);
		System.out.println("2");
	}*/
}