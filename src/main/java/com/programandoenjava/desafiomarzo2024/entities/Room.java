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
public class Room {
    public Room(long room_id, RoomType type, double price) {
        this.room_id = room_id;
        this.type = type;
        this.price = price;
    }

    public Room() {
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long room_id;
    @Enumerated(EnumType.STRING)
    private RoomType type;
    private double price;
    @JsonIgnore
    @OneToMany (mappedBy = "room")
    private Set<Reservation> reservation = new HashSet<>();
}
