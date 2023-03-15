package com.parkinglot.model;

import com.parkinglot.common.VehicleType;

public class Bus extends Vehicle {

    public Bus(String licenseNumber){
        this.licenseNumber=licenseNumber;
        this.vehicleType= VehicleType.BUS;
    }
}
