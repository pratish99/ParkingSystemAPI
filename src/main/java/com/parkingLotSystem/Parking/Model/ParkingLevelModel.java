package com.parkingLotSystem.Parking.Model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLevelModel {
    private Integer levelId;
    private Integer bikeOccupied;
    private Integer carOccupied;
    private Integer busOccupied;
    private Integer bikeAvailable;
    private Integer carAvailable;
    private Integer busAvailable;
}
