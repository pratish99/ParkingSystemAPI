package com.parkingLotSystem.Parking.Service.Impl;

import com.parkingLotSystem.Parking.Constants.Constants;
import com.parkingLotSystem.Parking.Entity.ParkingLevel;
import com.parkingLotSystem.Parking.Entity.Slot;
import com.parkingLotSystem.Parking.Entity.Vehicle;
import com.parkingLotSystem.Parking.Entity.VehicleType;
import com.parkingLotSystem.Parking.Model.VehicleModel;
import com.parkingLotSystem.Parking.Repository.LevelRepository;
import com.parkingLotSystem.Parking.Repository.SlotRepository;
import com.parkingLotSystem.Parking.Repository.VehicleRepository;
import com.parkingLotSystem.Parking.Responses.Response;
import com.parkingLotSystem.Parking.Service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Optional;

import static com.parkingLotSystem.Parking.Entity.VehicleType.*;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private SlotRepository slotRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    private final Constants constants = new Constants();

    @Override
    public Response parkVehicle(VehicleModel vehicleModel) {
        if (vehicleRepository.existsById(vehicleModel.getRegistrationNumber())) {
            return new Response<>("Vehicle Already Parked! ", HttpStatus.NOT_ACCEPTABLE);
        }
        Slot getAvailableSlot = getAvailableSlot(vehicleModel.getVehicleType());
        if (getAvailableSlot != null) {
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
            updateTables(vehicle);
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

    private void updateTables(Vehicle vehicle) {
        Slot slot = slotRepository.findById(vehicle.getSlotId()).get();
        slot.setOccupied(false);
        slotRepository.save(slot);
        ParkingLevel parkingLevel = levelRepository.findById(slot.getLevelId()).get();
        setAvailability(parkingLevel, vehicle.getVehicleType(), false);
        levelRepository.save(parkingLevel);
    }

    private Slot getAvailableSlot(VehicleType type) {
        for (Slot slot : slotRepository.findAll()) {
            if (!slot.getOccupied() && slot.getSlotType() == type) {
                slot.setOccupied(true);
                Optional<ParkingLevel> parkingLevel = levelRepository.findById(slot.getLevelId());
                ParkingLevel entity = parkingLevel.get();
                setAvailability(entity, type, true);
                levelRepository.save(entity);
                return slot;
            }
        }
        return null;
    }

    private void setAvailability(ParkingLevel entity, VehicleType type, Boolean park) {
        if (park) {
            if (type == Bike) {
                entity.setBikeAvailable(entity.getBikeAvailable() - constants.ONE);
                entity.setBikeOccupied(entity.getBikeOccupied() + constants.ONE);
            } else if (type == Car) {
                entity.setCarAvailable(entity.getCarAvailable() - constants.ONE);
                entity.setCarOccupied(entity.getCarOccupied() + constants.ONE);
            } else {
                entity.setBusAvailable(entity.getBusAvailable() - constants.ONE);
                entity.setBusOccupied(entity.getBusOccupied() + constants.ONE);
            }
        } else {
            if (type == Bike) {
                entity.setBikeAvailable(entity.getBikeAvailable() + constants.ONE);
                entity.setBikeOccupied(entity.getBikeOccupied() - constants.ONE);
            } else if (type == Car) {
                entity.setCarAvailable(entity.getCarAvailable() + constants.ONE);
                entity.setCarOccupied(entity.getCarOccupied() - constants.ONE);
            } else {
                entity.setBusAvailable(entity.getBusAvailable() + constants.ONE);
                entity.setBusOccupied(entity.getBusOccupied() - constants.ONE);
            }
        }
    }
}
