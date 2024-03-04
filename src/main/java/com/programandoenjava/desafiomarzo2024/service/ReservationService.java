package com.programandoenjava.desafiomarzo2024.service;

import com.programandoenjava.desafiomarzo2024.entities.Reservation;
import com.programandoenjava.desafiomarzo2024.repository.IReservationRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationService {
    private final IReservationRepository reservationRepository;

    public ReservationService(IReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // Tarea 4: Crear Reservas para los Clientes
    public String createReserva(Reservation reserva) {
        reservationRepository.save(reserva);
        return "Reserva creada con éxito";
    }

    // Tarea 5: Cancelar Reservas
    public String cancelReserva(Long id) {
        reservationRepository.deleteById(id);
        return "Se ha eliminado la reserva con ´´exito";
    }

    // Tarea 6: Modificar Fechas de una Reserva Existente
    public Reservation findReserva(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public String updateReserva(Long id, Date newCheckIn, Date newCheckOut) {
        Reservation reserva = findReserva(id);
        if (reserva != null) {
            reserva.setCheckIn(newCheckIn);
            reserva.setCheckOut(newCheckOut);
            return "Se han modificado las fechas";
        }

        return "No se ha encontrado la reserva que busca";
    }

    // Tarea 7: Listar Todas las Reservas
    public List<Reservation> getAllReservation (){
        return reservationRepository.findAll();
    }

    // Tarea 9: Buscar Reservas por Nombre del Cliente
    public List<Reservation> findByClinete(String nombreCliente){
        return reservationRepository.findByCliente_Nombre(nombreCliente);
    }

}
