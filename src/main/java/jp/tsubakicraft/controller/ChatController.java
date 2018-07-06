package jp.tsubakicraft.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import jp.tsubakicraft.chat.IncomingMessage;
import jp.tsubakicraft.chat.MessagingError;
import jp.tsubakicraft.chat.OutgoingMessage;

@Controller
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @MessageMapping("/chat.send/{topic}")
	@SendTo("/topic/messages")
	public OutgoingMessage sendMessage(@DestinationVariable("topic") String topic, @Payload IncomingMessage message) {
		logger.info("sendMessage() {content: " +  message.getContent() + ", sender: " + message.getSender() + ", timestamp: " + message.getTimestamp() + "}");
		OutgoingMessage out = new OutgoingMessage(message.getType(), message.getContent(), message.getSender(), topic);
		return out;
	}
	
	@MessageMapping("/chat.adduser")
	@SendTo("/topic/messages")
	public OutgoingMessage addUser(@Payload IncomingMessage message, SimpMessageHeaderAccessor headerAccessor) {
		logger.info("addUser : " +  message.getType() + " (" + message.getSender());
		headerAccessor.getSessionAttributes().put("username", message.getSender());
		OutgoingMessage out = new OutgoingMessage(message.getType(), message.getContent(), message.getSender(), null);
		return out;
	}
	
	@MessageExceptionHandler
	@SendToUser("/queue/errors")
	public MessagingError handleException(Throwable exception) {
		MessagingError error = new MessagingError();
		error.setMessage(exception.getMessage());
		return error;
	}

}
