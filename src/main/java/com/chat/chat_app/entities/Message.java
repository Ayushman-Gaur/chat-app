package com.chat.chat_app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String sender;

    private String content;
    private LocalDateTime timeStamp;

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timeStamp=LocalDateTime.now();
    }

}
