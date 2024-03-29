package com.parkingLotSystem.Parking.Entity;

import com.parkingLotSystem.Parking.Enumerators.VehicleType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name="VEHICLE")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @Column(name = "ID")
    private String registrationNumber;
    @Column(name = "VEHICLE_TYPE")
    @Enumerated(value = EnumType.STRING)
    private VehicleType vehicleType;
    @Column(name = "SLOT_ID")
    private Integer slotId;
    @Column(name = "PARKING_TIME")
    private LocalTime localTime;
    @OneToOne
    @JoinColumn(name = "SLOT_ID", updatable = false, insertable = false)
    private Slot slot;
}
