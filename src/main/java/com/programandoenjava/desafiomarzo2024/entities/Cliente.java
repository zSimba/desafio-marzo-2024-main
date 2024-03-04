package com.programandoenjava.desafiomarzo2024.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Cliente {



    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long cliente_id;
    private String nombre;
    private String Apellido;
    private String tlf;
    @OneToMany (mappedBy = "cliente")
    private Set<Reservation> reservation;
}
