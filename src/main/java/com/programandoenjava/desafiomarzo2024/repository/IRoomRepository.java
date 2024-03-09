package com.programandoenjava.desafiomarzo2024.repository;

import com.programandoenjava.desafiomarzo2024.entities.Room;
import com.programandoenjava.desafiomarzo2024.entities.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IRoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByType (RoomType type);

    @Query ("select ro from Room ro where ro.room_id not in (" +
            "select room.room_id from Reservation re join re.room room " +
            "where re.checkOut >= :start and re.checkIn <= :end) " +
            "and ro.type = :type")
    List<Room> findAvailable2 (LocalDate start, LocalDate end, RoomType type);

}
