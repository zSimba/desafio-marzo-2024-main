package com.programandoenjava.desafiomarzo2024.service;

import com.programandoenjava.desafiomarzo2024.entities.Room;

import java.util.List;

public interface IRoomService {

    // Agregar habitación al sistema
    String saveRoom(Room room);

    // Obtener lista de todas las habitaciones
    List<Room> getAllRooms();

    // Update habitación
    String updateRoom(Long id, String type, double price);

    // Encontrar una habitación por Id
    Room findRoom(Long id);

}
