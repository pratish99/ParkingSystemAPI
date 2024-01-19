package com.parkingLotSystem.Parking.Service.Impl;

import com.parkingLotSystem.Parking.Constants.Constants;
import com.parkingLotSystem.Parking.Entity.ParkingLevel;
import com.parkingLotSystem.Parking.Entity.Slot;
import com.parkingLotSystem.Parking.Entity.Vehicle;
import com.parkingLotSystem.Parking.Enumerators.VehicleType;
import com.parkingLotSystem.Parking.Model.ParkingLevelModel;
import com.parkingLotSystem.Parking.Model.SlotModel;
import com.parkingLotSystem.Parking.Model.VehicleModel;
import com.parkingLotSystem.Parking.Repository.LevelRepository;
import com.parkingLotSystem.Parking.Repository.VehicleRepository;
import com.parkingLotSystem.Parking.Responses.Response;
import com.parkingLotSystem.Parking.Service.LevelService;
import com.parkingLotSystem.Parking.Service.SlotService;
import com.parkingLotSystem.Parking.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class LevelServiceImpl implements LevelService {
    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private SlotService slotService;



    Constants constants = new Constants();

    @Override
    public Response<String> addLevel(ParkingLevelModel parkingLevelModel) {
        System.out.println(parkingLevelModel.getLevelId());
        ParkingLevel parkingLevel = ParkingLevel.builder()
                .levelId(parkingLevelModel.getLevelId())
                .bikeAvailable(parkingLevelModel.getBikeAvailable())
                .carAvailable(parkingLevelModel.getCarAvailable())
                .busAvailable(parkingLevelModel.getBusAvailable())
                .bikeOccupied(constants.ZERO)
                .carOccupied(constants.ZERO)
                .busOccupied(constants.ZERO)
                .build();
        levelRepository.save(parkingLevel);
        slotService.addSlots(parkingLevel.getLevelId(), parkingLevelModel);
        return new Response<>("Level Added Successfully.");
    }

    /*@Override
    public Response<String> decreaseLevel(Integer id) {
        if (levelRepository.existsById(id)) {
            List<SlotModel> slotModelList = slotService.getSlots(id);
            for(SlotModel slotModel : slotModelList){
                vehicleService.deleteBySlotId(slotModel.getSlotId());
            }
            slotService.deleteSlots(id);
            return new Response<>("Level Deleted Successfully");
        }
        return new Response<>("Level Not Found", HttpStatus.NOT_FOUND);
    }
*/
    @Override
    public Response<List<ParkingLevelModel>> getAllLevels() {
        List<ParkingLevelModel> myList = new ArrayList<>();
        for (ParkingLevel parking : levelRepository.findAllLevels()) {
            ParkingLevelModel parkingModel = ParkingLevelModel.builder().levelId(parking.getLevelId())
                    .bikeOccupied(parking.getBikeOccupied())
                    .carOccupied(parking.getCarOccupied())
                    .busOccupied(parking.getBusOccupied())
                    .bikeAvailable(parking.getBikeAvailable())
                    .carAvailable(parking.getCarAvailable())
                    .busAvailable(parking.getBusAvailable())
                    .slotList(listOfSlotModels(parking.getSlotList()))
                    .build();
            myList.add(parkingModel);
        }
        if (!myList.isEmpty()) return new Response<>(myList);
        return new Response<>(null, HttpStatus.NO_CONTENT);

    }




    @Override
    public void updateLevel(Integer id, VehicleType type, Boolean park) {
        if(park){
            switch (type){
                case Bike:{
                    levelRepository.updateLevelBike(id);
                    break;
                }
                case Car:{
                    levelRepository.updateLevelCar(id);
                    break;
                }
                case Bus:{
                    levelRepository.updateLevelBus(id);
                    break;
                }
            }
        }
        else{
            switch (type){
                case Bike:{
                    levelRepository.decreaseLevelBike(id);
                    break;
                }
                case Car:{
                    levelRepository.decreaseLevelCar(id);
                    break;
                }
                case Bus:{
                    levelRepository.decreaseLevelBus(id);
                    break;
                }
            }
        }
    }

    @Override
    public void updateLevelTable(Integer id, VehicleType type, Boolean park) {
        ParkingLevel parkingLevel = levelRepository.findById(id).get();
        updateLevel(id, type, park);
    }

    @Override
    public Boolean existsById(Integer id) {
        return levelRepository.existsById(id);
    }

    @Override
    public void deleteById(Integer id) {
        levelRepository.deleteById(id);
    }

    private List<SlotModel> listOfSlotModels(List<Slot> list) {
        return list.stream().map(x -> new SlotModel(x.getSlotId(),
                x.getLevelId(), x.getSlotType(), x.getOccupied(),
                Optional.ofNullable(x.getVehicleDetails())
                        .map(value -> new VehicleModel(value.getRegistrationNumber(),
                                value.getVehicleType(), value.getSlotId()))
                        .orElse(null)
        )).toList();
    }


}
