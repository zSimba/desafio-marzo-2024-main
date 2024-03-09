package com.programandoenjava.desafiomarzo2024.controller;

import com.programandoenjava.desafiomarzo2024.dto.ReservationDto;
import com.programandoenjava.desafiomarzo2024.entities.Reservation;
import com.programandoenjava.desafiomarzo2024.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/reservas")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // Tarea 4: Crear Reservas para los Clientes

    @PostMapping("/crear")
    public String createReservation(@RequestBody ReservationDto reserva) {
        return reservationService.createReservation(reserva);
    }

    // Tarea 5: Cancelar Reservas
    @DeleteMapping("/cancelar")
    public String cancelarReserva(Long id) {
        return reservationService.cancelReserva(id);
    }

    // Tarea 6: Modificar Fechas de una Reserva Existente
    @PutMapping("/midificar/{id}")
    public String modificarReserva(@PathVariable Long id,
                                   @RequestParam(required = false, name = "starDate") LocalDate newCheckIn,
                                   @RequestParam(required = false, name = "endDate") LocalDate newCheckOut) {
        return reservationService.updateReserva(id, newCheckIn, newCheckOut);

    }

    // Tarea 7: Listar Todas las Reservas
    @GetMapping("/lista")
    public List<Reservation> getLista() {
        return reservationService.getAllReservation();
    }

    // Tarea 9: Buscar Reservas por Nombre del Cliente
    @GetMapping("/buscar/{nombreCliente}")
    public List<Reservation> findByNombreCliente(@PathVariable String nombreCliente) {
        return reservationService.findByCliente(nombreCliente);
    }
}
