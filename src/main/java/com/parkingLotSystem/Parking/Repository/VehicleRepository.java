package com.parkingLotSystem.Parking.Repository;

import com.parkingLotSystem.Parking.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    /*@Query("SELECT t FROM Thing t WHERE t.SLOT_ID = slotId")
    Vehicle findBySlot(Integer slotId);*/
}
