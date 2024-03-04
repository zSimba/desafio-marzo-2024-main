package com.programandoenjava.desafiomarzo2024.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Setter
@Getter
public class Reservation {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id_reservation;
    private Date checkIn;
    private Date checkOut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


}
