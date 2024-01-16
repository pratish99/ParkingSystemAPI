package com.parkingLotSystem.Parking.Model;

import com.parkingLotSystem.Parking.Entity.VehicleType;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleModel {
    private String registrationNumber;
    private VehicleType vehicleType;
    private Integer levelId;
    private Integer slotId;
}


