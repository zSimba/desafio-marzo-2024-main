package com.programandoenjava.desafiomarzo2024.service;

import com.programandoenjava.desafiomarzo2024.entities.Reservation;
import com.programandoenjava.desafiomarzo2024.entities.Room;
import com.programandoenjava.desafiomarzo2024.entities.RoomType;
import com.programandoenjava.desafiomarzo2024.repository.IRoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;


import java.util.*;

public class RoomServiceTest {

    private RoomService roomService;
    private IRoomRepository roomRepository;

    @BeforeEach
    public void setUp() {
        roomRepository = mock(IRoomRepository.class);
        roomService = new RoomService(roomRepository);
    }

    @Test
    public void testAddReservation_Success() {
        Room room = new Room();
        Set<Reservation> reservations = new HashSet<>();
        roomService.addReservation(room, reservations);
        assertEquals(reservations, room.getReservation());
    }

    @Test
    public void testSaveRoom_Success() {
        Room myRoom = new Room();
        String result = roomService.saveRoom(myRoom);
        assertEquals("Habitacion guardada con éxito", result);
        verify(roomRepository, times(1)).save(myRoom);
    }

    @Test
    public void testGetAllRooms() {
        List<Room> rooms = new ArrayList<>();
        when(roomRepository.findAll()).thenReturn(rooms);
        List<Room> result = roomService.getAllRooms();
        assertEquals(rooms, result);
    }

    @Test
    public void testFindRoom_Success() {
        Long id = 1L;
        Room room = new Room();
        when(roomRepository.findById(id)).thenReturn(Optional.of(room));
        Room result = roomService.findRoom(id);
        assertNotNull(result);
        assertEquals(room, result);
    }

    @Test
    public void testFindRoom_NotFound() {
        Long id = 1L;
        when(roomRepository.findById(id)).thenReturn(Optional.empty());
        Room result = roomService.findRoom(id);
        assertNull(result);
    }

    @Test
    public void testUpdateRoom_Success() {
        Long id = 1L;
        Room myRoom = new Room();
        RoomType newType = RoomType.INDIVIDUAL;
        double newPrice = 150.0;
        when(roomRepository.findById(id)).thenReturn(Optional.of(myRoom));
        String result = roomService.updateRoom(id, newType, newPrice);
        assertEquals("Habitación actualizada con éxito", result);
        assertEquals(newType, myRoom.getType());
        assertEquals(newPrice, myRoom.getPrice());
        verify(roomRepository, times(1)).save(myRoom);
    }

    @Test
    public void testUpdateRoom_NotFound() {
        Long id = 1L;
        RoomType newType = RoomType.PREMIUM;
        double newPrice = 150.0;
        when(roomRepository.findById(id)).thenReturn(Optional.empty());
        String result = roomService.updateRoom(id, newType, newPrice);
        assertEquals("No se ha encontrado la habitación", result);
    }
}
