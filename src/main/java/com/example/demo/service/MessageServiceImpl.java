package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.MessageController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 实现消息发送
 */
@Service
public class MessageServiceImpl {

	private Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendMsg(String queueName, String msg) {
		// 消息发送时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("消息发送时间:" + sdf.format(new Date()));
		logger.info("消息发送时间:" + sdf.format(new Date()));
		
		// rabbitTemplate 四个参数  1.exchange 交换器名称   2.eroutingKey 路由key 3.message 要发送的消息  4.messagePostProcessor 在发送消息之前应用于该消息的处理器
		rabbitTemplate.convertAndSend("test_exchange", queueName, msg, new MessagePostProcessor() {
			// 消息处理器： 
			// post发送消息
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				// 设定时间3s
				message.getMessageProperties().setHeader("x-delay", 600000);
				return message;
			}
		});
	}
}