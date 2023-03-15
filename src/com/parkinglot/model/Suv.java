package com.parkinglot.model;

import com.parkinglot.common.VehicleType;

public class Suv extends Vehicle {

    public Suv(String licenseNumber){
        this.licenseNumber=licenseNumber;
        this.vehicleType= VehicleType.SUV;
    }
}
