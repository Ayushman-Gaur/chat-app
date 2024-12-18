package com.chat.chat_app.services;

import com.chat.chat_app.entities.Message;
import com.chat.chat_app.entities.Room;

import java.util.List;

public interface IRoomService {
    Room joinRoomById(String roomId);

    Room getRoom(String roomId);
    List<Message> getMessage(String roomId);
}
