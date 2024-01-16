package com.parkingLotSystem.Parking.Repository;

import com.parkingLotSystem.Parking.Entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {
}
