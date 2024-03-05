package com.programandoenjava.desafiomarzo2024.service;

import com.programandoenjava.desafiomarzo2024.entities.Cliente;
import com.programandoenjava.desafiomarzo2024.entities.Reservation;
import com.programandoenjava.desafiomarzo2024.entities.Room;
import com.programandoenjava.desafiomarzo2024.repository.IClienteRepository;
import com.programandoenjava.desafiomarzo2024.repository.IReservationRepository;
import com.programandoenjava.desafiomarzo2024.repository.IRoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    private final IReservationRepository reservationRepository;
    private final IRoomRepository roomRepository;
    private final IClienteRepository clienteRepository;

    public ReservationService(IReservationRepository reservationRepository, IRoomRepository roomRepository, IClienteRepository clienteRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.clienteRepository = clienteRepository;
    }

    // Para crear una resrva, debe existir previamente el cliente y la habitacion a los cuales se asocia la reserva
    public String createReservation(Long roomId, Long clienteId, LocalDate checkIn, LocalDate checkOut) {
        Room room = roomRepository.findById(roomId).orElse(null);
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);

        if (room != null && cliente != null) {
            Reservation reservation = new Reservation();
            reservation.setCheckIn(checkIn);
            reservation.setCheckOut(checkOut);
            reservation.setRoom(room);
            reservation.setCliente(cliente);
            reservationRepository.save(reservation);
            return "Se ha creado con exito la reserva";
        }

        return "No se ha podido crear la reserva";

    }

    public String createReserva(Reservation reserva) {
        reservationRepository.save(reserva);
        return "Reserva creada con éxito";
    }

    public String cancelReserva(Long id) {
        reservationRepository.deleteById(id);
        return "Se ha eliminado la reserva con ´´exito";
    }

    public Reservation findReserva(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public String updateReserva(Long id, LocalDate newCheckIn, LocalDate newCheckOut) {
        Reservation reserva = findReserva(id);
        if (reserva != null) {
            reserva.setCheckIn(newCheckIn);
            reserva.setCheckOut(newCheckOut);
            return "Se han modificado las fechas";
        }

        return "No se ha encontrado la reserva que busca";
    }

    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    public List<Reservation> findByClinete(String nombreCliente) {
        return reservationRepository.findByCliente_Nombre(nombreCliente);
    }

}
