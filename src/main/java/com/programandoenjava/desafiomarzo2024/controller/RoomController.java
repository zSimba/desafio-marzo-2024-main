package com.programandoenjava.desafiomarzo2024.controller;

import com.programandoenjava.desafiomarzo2024.entities.Room;
import com.programandoenjava.desafiomarzo2024.entities.RoomType;
import com.programandoenjava.desafiomarzo2024.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/habitaciones")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/crear")
    public String createRoom(@RequestBody Room myRoom){
        return roomService.saveRoom(myRoom);
    }

    @GetMapping("/lista")
    public List<Room> getLista(){
        return roomService.getAllRooms();
    }

    @PutMapping("/actualizar/{id}")
    public String actualizarRoom(@PathVariable Long id,
                                 @RequestParam (required = false, name = "type") RoomType newType,
                                 @RequestParam (required = false, name = "price") double newPrice
                                 ){
        return roomService.updateRoom(id, newType, newPrice);
    }

}
