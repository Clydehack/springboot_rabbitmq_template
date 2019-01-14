package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.MessageServiceImpl;

@RestController
@RequestMapping("/msg")
public class MessageController {

//	private Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	private MessageServiceImpl messageService;

	@RequestMapping("/send")
	public void send(HttpServletRequest request, HttpServletResponse response) {
		messageService.sendMsg("test_queue_1", request.getParameter("test"));
	}
}