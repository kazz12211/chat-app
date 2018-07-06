package jp.tsubakicraft.chat;

import java.util.Date;

public class OutgoingMessage {

	private MessageType type;
	private String content;
	private String sender;
	private Date timestamp;
	private String topic;
	
	public OutgoingMessage(MessageType type, String content, String sender, String topic) {
		this.type = type;
		this.content = content;
		this.sender = sender;
		this.topic = topic;
		this.timestamp = new Date();
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	
}
