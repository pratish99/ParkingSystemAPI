package com.parkingLotSystem.Parking.Repository;

import com.parkingLotSystem.Parking.Entity.ParkingLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<ParkingLevel, Integer> {
}
