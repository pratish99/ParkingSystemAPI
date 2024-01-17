package com.parkingLotSystem.Parking.Model;

import com.parkingLotSystem.Parking.Entity.Slot;
import com.parkingLotSystem.Parking.Entity.Vehicle;
import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ParkingLevelModel {
    private Integer levelId;
    private Integer bikeOccupied;
    private Integer carOccupied;
    private Integer busOccupied;
    private Integer bikeAvailable;
    private Integer carAvailable;
    private Integer busAvailable;
    private List<SlotModel> slotList;
}
