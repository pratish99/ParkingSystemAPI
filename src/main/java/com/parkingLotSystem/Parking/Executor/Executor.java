package com.parkingLotSystem.Parking.Executor;

import com.parkingLotSystem.Parking.Model.VehicleModel;
import com.parkingLotSystem.Parking.Service.VehicleService;
import jdk.jfr.Enabled;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Component
@EnableScheduling
@Slf4j
public class Executor {

    @Autowired
    private VehicleService vehicleService;

    private LocalTime localTime;
    @Scheduled(cron = "0 0/1 * * * ?")
    //@Scheduler
    public void forceUnpark(){
        List<VehicleModel> vehicles = vehicleService.allVehicles();
        for(VehicleModel vehicle : vehicles){
            LocalTime value = vehicle.getLocalTime().minus(Duration.ofHours(1));
            if(value.isAfter(vehicle.getLocalTime())){
                System.out.println(vehicle.getRegistrationNumber() + "Unparked ");
                vehicleService.unparkVehicle(vehicle.getRegistrationNumber());
            }
        }
    }

}
