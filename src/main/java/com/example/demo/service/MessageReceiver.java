package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
 
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 消息消费者
 */
@Component
public class MessageReceiver {

	private Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
	
	// 消费test_queue_1队列的信息
	@RabbitListener(queues = "test_queue_1")
	public void receive(String msg) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("消息接收时间:"+sdf.format(new Date()));
		System.out.println("接收到的消息:"+msg);
		logger.info("消息接收时间:"+sdf.format(new Date()));
		logger.info("接收到的消息:"+msg);
	}
}