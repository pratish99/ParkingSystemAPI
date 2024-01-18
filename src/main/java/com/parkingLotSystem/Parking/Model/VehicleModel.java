package com.parkingLotSystem.Parking.Model;

import com.parkingLotSystem.Parking.Enumerators.VehicleType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleModel {
    private String registrationNumber;
    private VehicleType vehicleType;
    private Integer slotId;
}


