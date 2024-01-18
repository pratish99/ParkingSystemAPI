package com.parkingLotSystem.Parking.Service;

import com.parkingLotSystem.Parking.Model.VehicleModel;
import com.parkingLotSystem.Parking.Responses.Response;

public interface VehicleService {
    void save(VehicleModel vehicleModel);
    Response parkVehicle(VehicleModel vehicleModel);
    Response unparkVehicle(String registrationNumber);
    Response getVehicle(String registrationNumber);
}
