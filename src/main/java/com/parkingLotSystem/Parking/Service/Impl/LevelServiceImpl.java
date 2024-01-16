package com.parkingLotSystem.Parking.Service.Impl;
import ch.qos.logback.classic.model.LevelModel;
import com.parkingLotSystem.Parking.Entity.ParkingLevel;
import com.parkingLotSystem.Parking.Entity.Slot;
import com.parkingLotSystem.Parking.Entity.Vehicle;
import com.parkingLotSystem.Parking.Entity.VehicleType;
import com.parkingLotSystem.Parking.Model.ParkingLevelModel;
import com.parkingLotSystem.Parking.Repository.LevelRepository;
import com.parkingLotSystem.Parking.Repository.SlotRepository;
import com.parkingLotSystem.Parking.Repository.VehicleRepository;
import com.parkingLotSystem.Parking.Responses.Response;
import com.parkingLotSystem.Parking.Service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.parkingLotSystem.Parking.Entity.VehicleType.*;


@Service
public class LevelServiceImpl implements LevelService {
    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private SlotRepository slotRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Response<String> addLevel(ParkingLevelModel parkingLevelModel) {
        final int defValue = 0;
        ParkingLevel parkingLevel = ParkingLevel.builder()
                .levelId(parkingLevelModel.getLevelId())
                .bikeAvailable(parkingLevelModel.getBikeAvailable())
                .carAvailable(parkingLevelModel.getCarAvailable())
                .busAvailable(parkingLevelModel.getBusAvailable())
                .bikeOccupied(defValue)
                .carOccupied(defValue)
                .busOccupied(defValue)
                .build();
        levelRepository.save(parkingLevel);
        addSlots(parkingLevel);
        return new Response<>("Level Added Successfully.");
    }

    @Override
    public Response<String> decreaseLevel(Integer id) {
        if(levelRepository.existsById(id)){
            for (Vehicle vehicle : vehicleRepository.findAll()) {
                if (Objects.equals(vehicle.getLevelId(), id)) {
                    vehicleRepository.delete(vehicle);
                }
            }
            for (Slot slot : slotRepository.findAll()) {
                if (Objects.equals(slot.getLevelId(), id)) {
                    slotRepository.delete(slot);
                }
            }
            levelRepository.deleteById(id);
            return new Response<>("Level Deleted Successfully");
        }
        return new Response<>("Level Not Found", HttpStatus.NOT_FOUND);
    }

    @Override
    public Response<List<ParkingLevelModel>> getAllLevels() {
        List<ParkingLevel> levels = levelRepository.findAll();



        return new Response<>(levels.stream().map(level -> new ParkingLevelModel(level.getLevelId(),
                            level.getBikeAvailable(),level.getCarAvailable(),level.getBusAvailable(),
                            level.getBikeOccupied(),level.getCarOccupied(),level.getBusOccupied())).toList());

    }

    private void addSlots(ParkingLevel parkingLevel){
        List<Slot> slots = new ArrayList<>();
        for(int i = 0; i<parkingLevel.getBikeAvailable(); i++){
            Slot slot = Slot.builder().levelId(parkingLevel.getLevelId()).slotType(Bike).occupied(false).build();
            slots.add(slot);
        }
        for(int i = 0; i<parkingLevel.getCarAvailable(); i++){
            Slot slot = Slot.builder().levelId(parkingLevel.getLevelId()).slotType(Car).occupied(false).build();
            slots.add(slot);
        }
        for(int i = 0; i<parkingLevel.getBusAvailable(); i++){
            Slot slot = Slot.builder().levelId(parkingLevel.getLevelId()).slotType(Bus).occupied(false).build();
            slots.add(slot);
        }
        slotRepository.saveAll(slots);
    }
}
