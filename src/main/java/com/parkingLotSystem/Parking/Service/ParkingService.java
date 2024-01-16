package com.parkingLotSystem.Parking.Service;

import com.parkingLotSystem.Parking.Entity.Vehicle;
import com.parkingLotSystem.Parking.Model.VehicleModel;
import com.parkingLotSystem.Parking.Responses.Response;

public interface ParkingService {
    Response parkVehicle(VehicleModel vehicleModel);
    Response unparkVehicle(String registrationNumber);
    Response getVehicle(String registrationNumber);
}
