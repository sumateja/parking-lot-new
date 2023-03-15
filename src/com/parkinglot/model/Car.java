package com.parkinglot.model;

import com.parkinglot.common.VehicleType;

public class Car extends Vehicle {

    public Car(String licenseNumber){
        this.licenseNumber=licenseNumber;
        this.vehicleType= VehicleType.CAR;
    }
}
