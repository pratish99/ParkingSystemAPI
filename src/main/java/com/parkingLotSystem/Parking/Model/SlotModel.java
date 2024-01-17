package com.parkingLotSystem.Parking.Model;

import com.parkingLotSystem.Parking.Entity.VehicleType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SlotModel {
    private Integer slotId;
    private Integer levelId;
    private VehicleType slotType;
    private Boolean occupied;
    private VehicleModel vehicleDetails;
}
