package com.parkingLotSystem.Parking.Service;

import ch.qos.logback.classic.model.LevelModel;
import com.parkingLotSystem.Parking.Entity.ParkingLevel;
import com.parkingLotSystem.Parking.Enumerators.VehicleType;
import com.parkingLotSystem.Parking.Model.ParkingLevelModel;
import com.parkingLotSystem.Parking.Responses.Response;

import java.util.List;

public interface LevelService{
    Response<String> addLevel(ParkingLevelModel parkingLevelModel);

    Response<List<ParkingLevelModel>> getAllLevels();
    void updateLevel(Integer id, VehicleType type, Boolean park);
    void updateLevelTable(Integer id, VehicleType vehicleType, Boolean park);
    Boolean existsById(Integer id);
    void deleteById(Integer id);
}
