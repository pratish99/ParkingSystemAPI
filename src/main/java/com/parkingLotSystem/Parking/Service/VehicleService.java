package com.parkingLotSystem.Parking.Service;

import com.parkingLotSystem.Parking.Entity.Vehicle;
import com.parkingLotSystem.Parking.Model.VehicleModel;
import com.parkingLotSystem.Parking.Responses.Response;

import java.util.List;

public interface VehicleService {
    Response parkVehicle(VehicleModel vehicleModel);
    Response unparkVehicle(String registrationNumber);
    Response getVehicle(String registrationNumber);
    void deleteBySlotId(Integer id);
    Response<String> decreaseLevel(Integer id);

    List<VehicleModel> allVehicles();

}
