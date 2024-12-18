package com.chat.chat_app.services;

import com.chat.chat_app.entities.Message;
import com.chat.chat_app.entities.Room;
import com.chat.chat_app.repositories.RoomRepositories;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
@Service

@Getter
@Setter
public class RoomService implements IRoomService{

    private final RoomRepositories roomRepositories;

    public RoomService(RoomRepositories roomRepositories) {
        this.roomRepositories = roomRepositories;
    }

    @Override
    public Room joinRoomById(String roomId) {
        Room room= roomRepositories.findByRoomId(roomId);
        if(room==null){
            throw new IllegalArgumentException("Room not found");
        }
        return room;
    }


    @Override
    public Room getRoom(String roomId) {
        Room existingRoom = roomRepositories.findByRoomId(roomId);
        if(existingRoom!=null){
            throw new IllegalArgumentException("Room already exists");
        }

        Room room = new Room();
        room.setRoomId(roomId);
        return roomRepositories.save(room);
    }

    @Override
    public List<Message> getMessage(String roomId) {
        Room room = roomRepositories.findByRoomId(roomId);
        if (room == null) {
            throw new IllegalArgumentException("Room not found");
        }
        List<Message> messages = room.getMessages();
        System.out.println("Fetched messages: " + messages);
        return messages != null ? messages : new ArrayList<>();
    }
}
