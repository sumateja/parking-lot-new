package com.parkinglot.model;

import com.parkinglot.common.VehicleType;

public class Scooter extends Vehicle{

    public Scooter(String licenseNumber){
        this.licenseNumber =licenseNumber;
        this.vehicleType = VehicleType.SCOOTER;
    }


}
