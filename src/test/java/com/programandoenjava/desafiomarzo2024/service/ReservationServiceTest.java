package com.programandoenjava.desafiomarzo2024.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.programandoenjava.desafiomarzo2024.dto.ReservationDto;
import com.programandoenjava.desafiomarzo2024.entities.Cliente;
import com.programandoenjava.desafiomarzo2024.entities.Reservation;
import com.programandoenjava.desafiomarzo2024.entities.Room;
import com.programandoenjava.desafiomarzo2024.repository.IClienteRepository;
import com.programandoenjava.desafiomarzo2024.repository.IReservationRepository;
import com.programandoenjava.desafiomarzo2024.repository.IRoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationServiceTest {

    private ReservationService reservationService;
    private IReservationRepository reservationRepository;
    private IRoomRepository roomRepository;
    private IClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        reservationRepository = mock(IReservationRepository.class);
        roomRepository = mock(IRoomRepository.class);
        clienteRepository = mock(IClienteRepository.class);
        reservationService = new ReservationService(reservationRepository, roomRepository, clienteRepository);
    }

    @Test
    public void testCreateReservation_Success() {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setRoomId(1L);
        reservationDto.setClienteId(1L);
        reservationDto.setCheckIn(LocalDate.now());
        reservationDto.setCheckOut(LocalDate.now().plusDays(3));

        Room room = new Room();
        room.setRoom_id(1L);
        Cliente cliente = new Cliente();
        cliente.setCliente_id(1L);

        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        String result = reservationService.createReservation(reservationDto);
        assertEquals("Se ha creado con exito la reserva", result);
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    public void testCreateReservation_Failure() {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setRoomId(1L);
        reservationDto.setClienteId(1L);
        reservationDto.setCheckIn(LocalDate.now());
        reservationDto.setCheckOut(LocalDate.now().plusDays(3));

        when(roomRepository.findById(1L)).thenReturn(Optional.empty());
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());
        String result = reservationService.createReservation(reservationDto);
        assertEquals("No se ha podido crear la reserva", result);
        verify(reservationRepository, never()).save(any(Reservation.class));
    }

    @Test
    public void testCreateReserva_Success() {
        Reservation reservation = new Reservation();
        String result = reservationService.createReserva(reservation);
        assertEquals("Reserva creada con éxito", result);
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    public void testCancelReserva_Success() {
        Long id = 1L;
        String result = reservationService.cancelReserva(id);
        assertEquals("Se ha eliminado la reserva con éxito", result);
        verify(reservationRepository, times(1)).deleteById(id);
    }

    @Test
    public void testFindReserva_Success() {
        Long id = 1L;
        Reservation reservation = new Reservation();
        when(reservationRepository.findById(id)).thenReturn(Optional.of(reservation));
        Reservation result = reservationService.findReserva(id);
        assertNotNull(result);
        assertEquals(reservation, result);
    }

    @Test
    public void testFindReserva_NotFound() {
        Long id = 1L;
        when(reservationRepository.findById(id)).thenReturn(Optional.empty());
        Reservation result = reservationService.findReserva(id);
        assertNull(result);
    }

    @Test
    public void testUpdateReserva_Success() {
        Long id = 1L;
        Reservation reserva = new Reservation();
        LocalDate newCheckIn = LocalDate.now();
        LocalDate newCheckOut = LocalDate.now().plusDays(5);
        when(reservationRepository.findById(id)).thenReturn(Optional.of(reserva));
        String result = reservationService.updateReserva(id, newCheckIn, newCheckOut);
        assertEquals("Se han modificado las fechas", result);
        assertEquals(newCheckIn, reserva.getCheckIn());
        assertEquals(newCheckOut, reserva.getCheckOut());
        verify(reservationRepository, times(1)).save(reserva);
    }

    @Test
    public void testUpdateReserva_NotFound() {
        Long id = 1L;
        LocalDate newCheckIn = LocalDate.now();
        LocalDate newCheckOut = LocalDate.now().plusDays(5);
        when(reservationRepository.findById(id)).thenReturn(Optional.empty());
        String result = reservationService.updateReserva(id, newCheckIn, newCheckOut);
        assertEquals("No se ha encontrado la reserva que busca", result);
    }

    @Test
    public void testGetAllReservation() {
        List<Reservation> reservations = new ArrayList<>();
        when(reservationRepository.findAll()).thenReturn(reservations);
        List<Reservation> result = reservationService.getAllReservation();
        assertEquals(reservations, result);
    }

    @Test
    public void testFindByCliente_Success() {
        String nombreCliente = "John Doe";
        List<Reservation> reservations = new ArrayList<>();
        when(reservationRepository.findByClienteName(nombreCliente)).thenReturn(reservations);
        List<Reservation> result = reservationService.findByCliente(nombreCliente);
        assertEquals(reservations, result);
    }
}