package com.programandoenjava.desafiomarzo2024.controller;

import com.programandoenjava.desafiomarzo2024.dto.RoomDto;
import com.programandoenjava.desafiomarzo2024.entities.Room;
import com.programandoenjava.desafiomarzo2024.entities.RoomType;
import com.programandoenjava.desafiomarzo2024.service.RoomService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/habitaciones")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    // Tarea 1: Registrar Nuevas Habitaciones
    @PostMapping("/crear")
    public String createRoom(@RequestBody Room myRoom){
        return roomService.saveRoom(myRoom);
    }

    // Tarea 2: Listar Todas las Habitaciones Disponibles
    @GetMapping("/lista")
    public List<Room> getLista(){
        return roomService.getAllRooms();
    }

    // Tarea 3: Actualizar Detalles de las Habitaciones
    @PutMapping("/actualizar/{id}")
    public String actualizarRoom(@PathVariable Long id,
                                 @RequestParam (required = false, name = "type") RoomType newType,
                                 @RequestParam (required = false, name = "price") double newPrice
                                 ){
        return roomService.updateRoom(id, newType, newPrice);
    }

    // Tarea 8: Buscar Habitaciones Disponibles por Fecha y Tipo
    @GetMapping("/buscar")
    public List<Room> findByDateType (@RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
                                      @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
                                      @RequestParam RoomType type){
        return roomService.findAvailable2(checkIn, checkOut, type);
    }



}
