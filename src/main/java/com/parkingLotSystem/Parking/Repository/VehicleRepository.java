package com.parkingLotSystem.Parking.Repository;

import com.parkingLotSystem.Parking.Entity.Vehicle;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    List<Vehicle> findBySlotId(Integer slotId);

    @Transactional
    @Modifying
    void deleteBySlotId(Integer id);
}
