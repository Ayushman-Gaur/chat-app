package com.chat.chat_app.controller;

import com.chat.chat_app.entities.Message;
import com.chat.chat_app.entities.Room;
import com.chat.chat_app.repositories.RoomRepositories;
import com.chat.chat_app.response.ApiResponse;
import com.chat.chat_app.services.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final IRoomService roomService;

    public RoomController(IRoomService roomService) {
        this.roomService = roomService;
    }


    @PostMapping("/room")
    public ResponseEntity<?> createRoom(@RequestBody String roomId){
        try {
            Room room = roomService.getRoom(roomId);
            return ResponseEntity.ok( room);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId){
        try {
            Room room = roomService.joinRoomById(roomId);
            return ResponseEntity.ok(room);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{roomId}/content")
    public ResponseEntity<?> content(@PathVariable String roomId){
        try {
            List<Message> messages = roomService.getMessage(roomId);
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
