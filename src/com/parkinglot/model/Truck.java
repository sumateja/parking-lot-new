package com.parkinglot.model;

import com.parkinglot.common.VehicleType;

public class Truck extends Vehicle {

    public Truck(String licenseNumber){
        this.licenseNumber=licenseNumber;
        this.vehicleType= VehicleType.TRUCK;
    }
}
