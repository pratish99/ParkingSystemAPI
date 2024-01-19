package com.parkingLotSystem.Parking.Service.Impl;

import com.parkingLotSystem.Parking.Constants.Constants;
import com.parkingLotSystem.Parking.Entity.ParkingLevel;
import com.parkingLotSystem.Parking.Entity.Vehicle;
import com.parkingLotSystem.Parking.Model.ParkingLevelModel;
import com.parkingLotSystem.Parking.Model.SlotModel;
import com.parkingLotSystem.Parking.Model.VehicleModel;
import com.parkingLotSystem.Parking.Repository.VehicleRepository;
import com.parkingLotSystem.Parking.Responses.Response;
import com.parkingLotSystem.Parking.Service.LevelService;
import com.parkingLotSystem.Parking.Service.SlotService;
import com.parkingLotSystem.Parking.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.parkingLotSystem.Parking.Enumerators.VehicleType.*;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private SlotService slotService;
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private LevelService levelService;
    private final Constants constants = new Constants();

    @Override
    public Response parkVehicle(VehicleModel vehicleModel) {
        if (vehicleRepository.existsById(vehicleModel.getRegistrationNumber())) {
            return new Response<>("Vehicle Already Parked! ", HttpStatus.NOT_ACCEPTABLE);
        }
        SlotModel getAvailableSlot = slotService.getAvailableSlot(vehicleModel.getVehicleType());
        if (getAvailableSlot != null) {
            levelService.updateLevelTable(getAvailableSlot.getLevelId(), vehicleModel.getVehicleType(), true);
            Vehicle vehicle = Vehicle.builder().registrationNumber(vehicleModel.getRegistrationNumber())
                    .slotId(getAvailableSlot.getSlotId())
                    .vehicleType(vehicleModel.getVehicleType()).build();
            vehicleRepository.save(vehicle);

            return new Response<>(VehicleModel.builder().registrationNumber(vehicle.getRegistrationNumber())
                    .vehicleType(vehicle.getVehicleType())
                    .slotId(vehicle.getSlotId()).build());
        }
        return new Response<>("No Slots Available! ", HttpStatus.NOT_FOUND);
    }

    @Override
    public Response unparkVehicle(String registrationNumber) {
        if (vehicleRepository.existsById(registrationNumber)) {
            Vehicle vehicle = vehicleRepository.findById(registrationNumber).get();
            vehicleRepository.deleteById(registrationNumber);
            SlotModel slotModel = slotService.updateSlotTable(vehicle.getSlotId());
            levelService.updateLevelTable(slotModel.getLevelId(), slotModel.getSlotType(), false);
            return new Response<>("Vehicle Unparked Successfully");
        }
        return new Response<>("Vehicle Not Found", HttpStatus.NOT_FOUND);
    }
    @Override
    public Response getVehicle(String registrationNumber) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(registrationNumber);
        VehicleModel vehicleModel = (vehicle.map(value -> new VehicleModel(
                        value.getRegistrationNumber(), value.getVehicleType(),
                        value.getSlotId())).orElse(null));
        return vehicleModel != null ? new Response<>(vehicleModel)
                                    : new Response<>("Vehicle Not Found", HttpStatus.NOT_FOUND);

    }
    @Override
    public Response<String> decreaseLevel(Integer id) {
        if (levelService.existsById(id)) {
            List<SlotModel> slotModelList = slotService.getSlots(id);
            for(SlotModel slotModel : slotModelList){
                vehicleRepository.deleteBySlotId(slotModel.getSlotId());
            }
            slotService.deleteSlots(id);
            levelService.deleteById(id);
            return new Response<>("Level Deleted Successfully");
        }
        return new Response<>("Level Not Found", HttpStatus.NOT_FOUND);
    }


    @Override
    public void deleteBySlotId(Integer id) {
        vehicleRepository.deleteBySlotId(id);
    }
}
