package com.parkinglot.model;

import com.parkinglot.common.VehicleType;

public class ElectricSuv extends Vehicle {

    public ElectricSuv(String licenseNumber){
        this.licenseNumber=licenseNumber;
        this.vehicleType= VehicleType.ELECTRIC_SUV;
    }
}
