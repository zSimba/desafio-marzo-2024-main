package com.programandoenjava.desafiomarzo2024.service;

import com.programandoenjava.desafiomarzo2024.entities.Reservation;
import com.programandoenjava.desafiomarzo2024.entities.Room;
import com.programandoenjava.desafiomarzo2024.entities.RoomType;
import com.programandoenjava.desafiomarzo2024.repository.IRoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class RoomService {

    private final IRoomRepository roomRepository;

    public RoomService(IRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // Agregar reserva a una habitación
    public void addReservation (Room room, Set<Reservation> reservas){
        room.setReservation(reservas);
    }

    // Tarea 1: Registrar Nuevas Habitaciones
    public String saveRoom(Room myRoom) {
        roomRepository.save(myRoom);
        return "Habitacion guardada con éxito";
    }


    // Tarea 2: Listar Todas las Habitaciones Disponibles
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }


    // Tarea 3: Actualizar Detalles de las Habitaciones
    public Room findRoom(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    public String updateRoom(Long id, RoomType newType, double newPrice) {
        Room myRoom = findRoom(id);
        if (myRoom != null) {
            myRoom.setType(newType);
            myRoom.setPrice(newPrice);
            return "Habitación actualizada con éxito";
        }

        return "No sse ha encontrado la habitación";
    }

    // Tarea 8: Buscar Habitaciones Disponibles por Fecha y Tipo
    public List<Room> findAvailable(Date startDate, Date endDate, RoomType type) {
        List<Room> rooms = roomRepository.findByType(type);
        List<Room> result = new ArrayList<>();
        for (Room room : rooms) {
            if (isAvailable(room, startDate, endDate)) {
                result.add(room);
            }
        }
        return result;

    }

    public boolean isAvailable(Room room, Date start, Date end) {
        Set<Reservation> reservas = room.getReservation();
        for (Reservation reserva : reservas) {
            Date checkIn = reserva.getCheckIn();
            Date checkOut = reserva.getCheckOut();
            if ((start.before(checkOut) || start.equals(checkOut)) && (end.after(checkIn) || start.equals(checkIn))) {
                return false;
            }
        }
        return true;
    }

}
