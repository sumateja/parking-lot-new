package com.parkinglot.model;

import com.parkinglot.common.VehicleType;

public abstract class Vehicle {

    String licenseNumber;

    VehicleType vehicleType;

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
