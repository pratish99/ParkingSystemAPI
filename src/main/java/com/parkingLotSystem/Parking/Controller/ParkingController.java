package com.parkingLotSystem.Parking.Controller;
import com.parkingLotSystem.Parking.Model.VehicleModel;
import com.parkingLotSystem.Parking.Responses.Response;
import com.parkingLotSystem.Parking.Service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @PostMapping("/park")
    public ResponseEntity parkVehicle(@RequestBody VehicleModel vehicleModel){
        Response response = parkingService.parkVehicle(vehicleModel);
        return new ResponseEntity<>(response.getReturnObject(), response.getHttpStatus());
    }

    @GetMapping("/getVehicle/{id}")
    public ResponseEntity getVehicle(@PathVariable("id") String registrationNumber){
        Response response = parkingService.getVehicle(registrationNumber);
        return new ResponseEntity<>(response.getReturnObject(), response.getHttpStatus());
    }

    @DeleteMapping("/unpark/{id}")
    public ResponseEntity unpPark(@PathVariable("id") String registrationNumber){
        Response response = parkingService.unparkVehicle(registrationNumber);
        return new ResponseEntity<>(response.getReturnObject(), response.getHttpStatus());
    }
}
