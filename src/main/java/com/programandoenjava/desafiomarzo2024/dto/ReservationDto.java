package com.programandoenjava.desafiomarzo2024.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
public class ReservationDto {


    private long roomId;
    private long clienteId;
    private LocalDate checkIn;
    private LocalDate checkOut;

}
