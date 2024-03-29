package com.parkingLotSystem.Parking.Entity;

import com.parkingLotSystem.Parking.Model.SlotModel;
import com.parkingLotSystem.Parking.Model.VehicleModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="PARKING_LEVEL")
@Builder
@NamedEntityGraph(name = "Level.slots", attributeNodes = {@NamedAttributeNode("slotList")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer levelId;
    @Column(name = "BIKE_OCCUPIED")
    private Integer bikeOccupied;
    @Column(name = "CAR_OCCUPIED")
    private Integer carOccupied;
    @Column(name = "BUS_OCCUPIED")
    private Integer busOccupied;
    @Column(name = "BIKE_AVAILABLE")
    private Integer bikeAvailable;
    @Column(name = "CAR_AVAILABLE")
    private Integer carAvailable;
    @Column(name = "BUS_AVAILABLE")
    private Integer busAvailable;
    @OneToMany
    @JoinColumn(name = "LEVEL_ID", referencedColumnName = "ID")
    private List<Slot> slotList;
    @OneToMany
    @JoinColumn(name = "SLOT_ID", referencedColumnName = "ID")
    private List<Vehicle> vehicleList;

}
