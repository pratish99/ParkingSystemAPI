package com.parkingLotSystem.Parking.Entity;

import jakarta.persistence.*;
import lombok.*;

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
    @OneToOne
    @JoinColumn(name = "SLOT_ID", updatable = false, insertable = false)
    private Slot slot;
}
