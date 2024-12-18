package com.chat.chat_app.controller;

import com.chat.chat_app.Playload.MessageRequest;
import com.chat.chat_app.entities.Message;
import com.chat.chat_app.entities.Room;
import com.chat.chat_app.repositories.RoomRepositories;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
@CrossOrigin("http://localhost:8080")
@Controller
public class ChatController {

    private final RoomRepositories roomRepositories;

    public ChatController(RoomRepositories roomRepositories) {
        this.roomRepositories = roomRepositories;
    }

    // for sending and receiving messages
    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(@DestinationVariable String roomId, @RequestBody MessageRequest request) throws Exception {
            Room room=roomRepositories.findByRoomId(request.getRoomId());
            Message message = new Message();


            message.setContent(request.getContent());
            message.setSender(request.getSender());
            message.setTimeStamp(LocalDateTime.now());


            if(room!=null){
                room.getMessages().add(message);
                roomRepositories.save(room);
            }else{
                throw new RuntimeException("Room not found");
            }
            return message;
    }
}
