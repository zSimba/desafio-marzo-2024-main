package com.programandoenjava.desafiomarzo2024.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Cliente {



    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long cliente_id;
    private String nombre;
    @JsonIgnore
    @OneToMany (mappedBy = "cliente")
    private Set<Reservation> reservation =  new HashSet<>();
}
