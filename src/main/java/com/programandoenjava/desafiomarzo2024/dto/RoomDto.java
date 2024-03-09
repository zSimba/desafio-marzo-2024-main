package com.programandoenjava.desafiomarzo2024.dto;

import com.programandoenjava.desafiomarzo2024.entities.RoomType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
public class RoomDto {

    private RoomType type;
    private LocalDate checkIn;
    private LocalDate checkOut;
}
