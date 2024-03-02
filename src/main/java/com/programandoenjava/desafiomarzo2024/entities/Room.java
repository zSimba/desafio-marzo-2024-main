package com.programandoenjava.desafiomarzo2024.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Room {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idHabitacion;
    private String type;
    private double price;
}
