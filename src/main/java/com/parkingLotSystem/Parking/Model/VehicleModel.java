package com.parkingLotSystem.Parking.Model;

import com.parkingLotSystem.Parking.Enumerators.VehicleType;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleModel {
    private String registrationNumber;
    private VehicleType vehicleType;
    private Integer slotId;
    private LocalTime localTime;
}


