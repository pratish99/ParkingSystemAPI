package com.parkingLotSystem.Parking.Service.Impl;

import com.parkingLotSystem.Parking.Entity.Slot;
import com.parkingLotSystem.Parking.Model.SlotModel;
import com.parkingLotSystem.Parking.Repository.SlotRepository;
import com.parkingLotSystem.Parking.Service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;

public class SlotServiceImpl implements SlotService {

    @Autowired
    private SlotRepository slotRepository;

    public void save(SlotModel slotModel){
        slotRepository.save(Slot.builder()
                .slotId(slotModel.getSlotId()).levelId(slotModel.getLevelId())
                .slotType(slotModel.getSlotType()).occupied(slotModel.getOccupied())
                .build());
    }
}
