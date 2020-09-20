package com.pw.thunderchat.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pw.thunderchat.model.Messages;
import com.pw.thunderchat.model.Notification;
import com.pw.thunderchat.service.NotificationService;

@RestController
public class NotificationController {

	@Autowired
	SimpMessagingTemplate simpMessageTemplate;

	@Autowired
	NotificationService notificationService;

	/**
	 * MOVER TODA A PARTE DE MANDAR A NOTIFICAÇÃO PARA O SERVICE DE NOTIFICAÇÕES!!!!
	 * @param message
	 * @param notification
	 * @throws Exception
	 */
	@MessageMapping("/send-notification")
	public void send(Message<Notification> message, @Payload Messages notification) throws Exception {

		if (!notification.getTo().startsWith("@"))
			return;

		
		this.notificationService.registerNotification(notification);
		simpMessageTemplate.convertAndSendToUser(notification.getTo(), "/queue/sendback", notification);
	}

	@GetMapping("/notifications/{id}")
	public Map<String, List<Messages>> getAllNotificationsFromUser(@PathVariable String id) {
		return Collections.singletonMap("notifications", this.notificationService.getAllNotificationById(id));
	}
	
	@PostMapping("/notifications")
	public String a(@RequestBody Messages msg) {
		this.notificationService.registerNotification(msg);
		return "IHULLL";
	}

}