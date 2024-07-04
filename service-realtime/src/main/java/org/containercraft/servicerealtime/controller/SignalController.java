package org.containercraft.servicerealtime.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.containercraft.servicerealtime.dto.RoomDTO;
import org.containercraft.servicerealtime.dto.RoomServiceDTO;
import org.containercraft.servicerealtime.entity.Room;
import org.containercraft.servicerealtime.entity.User;
import org.containercraft.servicerealtime.model.SignalData;
import org.containercraft.servicerealtime.service.RoomService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SignalController {
    private final RoomService roomService;


    @MessageMapping("/hi")
    @SendTo("/topic/hi")
    public String test(@Payload String message){
        return message;
    }

    @MessageMapping("/join")
    @SendTo("/topic/join")
    public RoomServiceDTO joinRoom(@Payload RoomServiceDTO dto){
            Room room = null;

            if(!roomService.existRoom(dto.roomId())) {
                room = roomService.save(new RoomDTO(dto.roomId()));
            }

            if(room == null) {
                room = roomService.findById(dto.roomId());
            }
            User userSave = User.builder()
                    .id(dto.userId())
                    .build();

            room.getUsers().add(userSave);

            return dto;
    }

    @MessageMapping("/leave")
    @SendTo("/topic/leave")
    public RoomServiceDTO leaveRoom(@Payload RoomServiceDTO dto){
        if(!roomService.existRoom(dto.roomId())) throw new NotResourceException("room is not exist");

        Room room = roomService.findById(dto.roomId());

        room.getUsers().removeIf(user -> user.getId().equals(dto.userId()));

        return dto;
    }

    @MessageMapping("/add_peer")
    @SendTo("/topic/add_peer")
    public SignalData addPeer(@Payload SignalData signalData){
        return signalData;
    }

    @MessageMapping("/remove_peer")
    @SendTo("/topic/remove_peer")
    public SignalData removePeer(@Payload SignalData signalData){
        return signalData;
    }

    @MessageMapping("/offer")
    @SendTo("/topic/offer")
    public SignalData offer(@Payload SignalData offer) {
        return offer;
    }

    @MessageMapping("/answer")
    @SendTo("/topic/answer")
    public SignalData answer(@Payload SignalData answer){
        return answer;
    }
}
