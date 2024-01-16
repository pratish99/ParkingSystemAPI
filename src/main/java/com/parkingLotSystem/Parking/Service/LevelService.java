package com.parkingLotSystem.Parking.Service;

import ch.qos.logback.classic.model.LevelModel;
import com.parkingLotSystem.Parking.Model.ParkingLevelModel;
import com.parkingLotSystem.Parking.Responses.Response;

import java.util.List;

public interface LevelService{
    Response<String> addLevel(ParkingLevelModel parkingLevelModel);
    Response<String> decreaseLevel(Integer id);
    Response<List<ParkingLevelModel>> getAllLevels();

}
