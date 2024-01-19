package com.parkingLotSystem.Parking.Service;

import com.parkingLotSystem.Parking.Entity.Slot;
import com.parkingLotSystem.Parking.Enumerators.VehicleType;
import com.parkingLotSystem.Parking.Model.ParkingLevelModel;
import com.parkingLotSystem.Parking.Model.SlotModel;

import java.util.List;

public interface SlotService {
    void addSlots(Integer levelId, ParkingLevelModel parkingLevelModel);
    List<SlotModel> findByLevelId(Integer levelId);
    SlotModel updateSlotTable(Integer id);
    SlotModel getAvailableSlot(VehicleType vehicleType);
    void deleteSlots(Integer id);
    List<SlotModel> getSlots(Integer id);
}
