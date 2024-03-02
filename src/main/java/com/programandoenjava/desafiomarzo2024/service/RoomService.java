package com.programandoenjava.desafiomarzo2024.service;

import com.programandoenjava.desafiomarzo2024.entities.Room;
import com.programandoenjava.desafiomarzo2024.repository.IRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements IRoomService {

    private final IRoomRepository roomRepository;

    public RoomService(IRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public String saveRoom(Room myRoom) {
        roomRepository.save(myRoom);
        return "Habitacion guardada con éxito";
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room findRoom(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public String updateRoom(Long id, String newType, double newPrice) {
        Room myRoom = findRoom(id);
        if (myRoom != null){
            myRoom.setType(newType);
            myRoom.setPrice(newPrice);
            return "Habitación actualizada con éxito";
        }

        return "No sse ha encontrado la habitación";
    }

}
