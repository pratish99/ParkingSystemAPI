package com.parkingLotSystem.Parking.Controller;

import com.parkingLotSystem.Parking.Model.ParkingLevelModel;
import com.parkingLotSystem.Parking.Responses.Response;
import com.parkingLotSystem.Parking.Service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LevelController {
    @Autowired
    private LevelService LevelService;

    @PostMapping("/addLevel")
    public ResponseEntity<String> addLevel(@RequestBody ParkingLevelModel parkingLevelModel){
        Response<String> response = LevelService.addLevel(parkingLevelModel);
        return new ResponseEntity<>(response.getReturnObject(), response.getHttpStatus());
    }

    @DeleteMapping("/deleteLevel/{id}")
    public ResponseEntity<String> deleteLevel(@PathVariable Integer id){
        Response<String> response = LevelService.decreaseLevel(id);
        return new ResponseEntity<>(response.getReturnObject(), response.getHttpStatus());
    }

    @GetMapping("/parking-stats")
    public ResponseEntity<List<ParkingLevelModel>> allLevels(){
        Response<List<ParkingLevelModel>> response = LevelService.getAllLevels();
        return new ResponseEntity<>(response.getReturnObject(), response.getHttpStatus());
    }

    /*@GetMapping("/statistics")
    public ResponseEntity getStatistics(){
        Response response = LevelService.getStatistics();
        return new ResponseEntity<>(response.getReturnObject(), response.getHttpStatus());
    }*/
}
