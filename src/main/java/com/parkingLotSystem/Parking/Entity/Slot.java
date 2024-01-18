package com.parkingLotSystem.Parking.Entity;

import com.parkingLotSystem.Parking.Enumerators.VehicleType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="SLOT")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer slotId;
    @Column(name = "LEVEL_ID")
    private Integer levelId;
    @Column(name = "SLOT_TYPE")
    @Enumerated(value = EnumType.STRING)
    private VehicleType slotType;
    @Column(name = "OCCUPIED")
    private Boolean occupied;
    @OneToOne(mappedBy = "slot")
    private Vehicle vehicleDetails;
}
