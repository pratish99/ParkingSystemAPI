package com.parkingLotSystem.Parking.Service.Impl;

import com.parkingLotSystem.Parking.Constants.Constants;
import com.parkingLotSystem.Parking.Entity.ParkingLevel;
import com.parkingLotSystem.Parking.Entity.Slot;
import com.parkingLotSystem.Parking.Enumerators.VehicleType;
import com.parkingLotSystem.Parking.Model.ParkingLevelModel;
import com.parkingLotSystem.Parking.Model.SlotModel;
import com.parkingLotSystem.Parking.Model.VehicleModel;
import com.parkingLotSystem.Parking.Repository.SlotRepository;
import com.parkingLotSystem.Parking.Service.LevelService;
import com.parkingLotSystem.Parking.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.parkingLotSystem.Parking.Enumerators.VehicleType.*;

@Service
public class SlotServiceImpl implements com.parkingLotSystem.Parking.Service.SlotService {

    @Autowired
    private SlotRepository slotRepository;

    Constants constants = new Constants();
    @Override
    public void addSlots(Integer levelId, ParkingLevelModel parkingLevelModel) {
        List<Slot> slots = new ArrayList<>();
        for(int i = 0; i<parkingLevelModel.getBikeAvailable(); i++){
            Slot slot = Slot.builder().levelId(levelId).slotType(Bike).occupied(false).build();
            slots.add(slot);
        }
        for(int i = 0; i<parkingLevelModel.getCarAvailable(); i++){
            Slot slot = Slot.builder().levelId(levelId).slotType(Car).occupied(false).build();
            slots.add(slot);
        }
        for(int i = 0; i<parkingLevelModel.getBusAvailable(); i++){
            Slot slot = Slot.builder().levelId(levelId).slotType(Bus).occupied(false).build();
            slots.add(slot);
        }
        slotRepository.saveAll(slots);
    }

    @Override
    public List<SlotModel> findByLevelId(Integer levelId) {
        return slotRepository.findByLevelId(levelId).stream().map(x -> new SlotModel(
                x.getSlotId(), x.getLevelId(), x.getSlotType(), x.getOccupied(), null)).toList();
    }

    @Override
    public SlotModel updateSlotTable(Integer id) {
        Slot slot = slotRepository.findById(id).get();
        slot.setOccupied(false);
        slot.setVehicleDetails(null);
        slotRepository.save(slot);
        return new SlotModel(slot.getSlotId(), slot.getLevelId(), slot.getSlotType(), slot.getOccupied(), null);
    }

    @Override
    public SlotModel getAvailableSlot(VehicleType type) {
        List<Slot> slot = slotRepository.findVacant(type);
        if(slot.isEmpty()) return null;
        Slot availableSlot = slot.get(constants.ZERO);
        availableSlot.setOccupied(true);
        return new SlotModel(availableSlot.getSlotId(), availableSlot.getLevelId(),
                availableSlot.getSlotType(), availableSlot.getOccupied(), null);
    }

    @Override
    public List<SlotModel> getSlots(Integer id){
        for (Slot slot: slotRepository.findByLevelId(id)) slot.setVehicleDetails(null);
        return slotRepository.findByLevelId(id).stream().map(x -> new SlotModel(x.getSlotId(),
                x.getLevelId(), x.getSlotType(), x.getOccupied(), null)).toList();
    }

    @Override
    public void deleteSlots(Integer id) {
        for(SlotModel slotModel : findByLevelId(id)){
            slotRepository.deleteById(slotModel.getSlotId());
        }
    }


}
