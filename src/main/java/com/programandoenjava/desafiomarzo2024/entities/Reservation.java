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
    private long id;
    private String nombreCliente;
    private Date checkIn;
    private Date checkOut;
    @OneToMany
    @JoinColumn (name = "idHabitacion")
    private Set<Room> room;


}
