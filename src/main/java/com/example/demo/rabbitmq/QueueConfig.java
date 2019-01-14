package com.example.demo.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 队列配置
 */
@Configuration
public class QueueConfig {

	// 使用的是 CustomExchange ,不是 DirectExchange  自定义交换器?
	// 交换机路由的几种类型:Direct Exchange:直接匹配,通过Exchange名称+RountingKey来发送与接收消息
	// Exchange：消息交换机，它指定消息按什么规则，路由到哪个队列
	@Bean
	public CustomExchange delayExchange() {
		Map<String, Object> args = new HashMap<>();
		args.put("x-delayed-type", "direct");
		return new CustomExchange("test_exchange", "x-delayed-message", true, false, args);
	}

	// 队列 test_queue_1  Queue：消息队列载体，每个消息都会被投入到一个或多个队列
	@Bean
	public Queue queue() {
		Queue queue = new Queue("test_queue_1", true);
		return queue;
	}

	// Binding：绑定，它的作用就是把exchange和queue按照路由规则绑定起来。
	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(delayExchange()).with("test_queue_1").noargs();
	}
}