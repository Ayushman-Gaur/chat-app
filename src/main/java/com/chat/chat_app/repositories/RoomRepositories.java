package com.chat.chat_app.repositories;

import com.chat.chat_app.entities.Room;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepositories extends MongoRepository<Room,String> {
    //get room using room id

    Room findByRoomId(String roomId);
}
