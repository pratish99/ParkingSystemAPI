package com.parkingLotSystem.Parking.Repository;

import com.parkingLotSystem.Parking.Entity.Slot;
import com.parkingLotSystem.Parking.Enumerators.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {
    @Query("select a from Slot a where a.occupied = false and a.slotType = :vehicleType ")
    List<Slot> findVacant(@Param("vehicleType") VehicleType vehicleType);
}
