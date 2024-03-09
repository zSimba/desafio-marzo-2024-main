package com.programandoenjava.desafiomarzo2024.repository;

import com.programandoenjava.desafiomarzo2024.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {

    @Query ("select r from Reservation r left join fetch r.room ro left join fetch r.cliente c where c.nombre = :nombre ")
    List<Reservation> findByClienteName (@Param("nombre") String nombre);

    @Query ("select r from Reservation r left join fetch r.room ro left join fetch r.cliente c ")
    List<Reservation> findAll ();



}
