package com.parkingLotSystem.Parking.Repository;

import com.parkingLotSystem.Parking.Entity.ParkingLevel;
import com.parkingLotSystem.Parking.Entity.Vehicle;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelRepository extends JpaRepository<ParkingLevel, Integer> {

    @Modifying
    @Transactional
    @Query("update ParkingLevel pl set pl.bikeAvailable = pl.bikeAvailable - 1, pl.bikeOccupied = pl.bikeOccupied + 1 WHERE pl.id = :id")
    void updateLevelBike(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("update ParkingLevel pl set pl.carAvailable = pl.carAvailable - 1, pl.carOccupied = pl.carOccupied + 1 WHERE pl.id = :id")
    void updateLevelCar(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("update ParkingLevel pl set pl.busAvailable = pl.busAvailable - 1, pl.busOccupied = pl.busOccupied + 1 WHERE pl.id = :id")
    void updateLevelBus(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("update ParkingLevel pl set pl.bikeAvailable = pl.bikeAvailable + 1, pl.bikeOccupied = pl.bikeOccupied - 1 WHERE pl.id = :id")
    void decreaseLevelBike(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("update ParkingLevel pl set pl.carAvailable = pl.carAvailable + 1, pl.carOccupied = pl.carOccupied - 1 WHERE pl.id = :id")
    void decreaseLevelCar(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("update ParkingLevel pl set pl.busAvailable = pl.busAvailable + 1, pl.busOccupied = pl.busOccupied - 1 WHERE pl.id = :id")
    void decreaseLevelBus(@Param("id") Integer id);



    //@EntityGraph("Level.slots")
    @Query("select l from ParkingLevel l left join fetch l.slotList")
    List<ParkingLevel> findAllLevels();

}
