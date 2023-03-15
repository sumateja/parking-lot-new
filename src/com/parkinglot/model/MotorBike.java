package com.parkinglot.model;

import com.parkinglot.common.InvalidVehicleNumberException;
import com.parkinglot.common.ParkingFullException;
import com.parkinglot.common.ParkingNotAllowedForVehicleException;
import com.parkinglot.common.VehicleType;
import com.parkinglot.ticketing.Ticket;

public class MotorBike extends Vehicle{

    public MotorBike(String licenseNumber){
        this.licenseNumber =licenseNumber;
        this.vehicleType = VehicleType.MOTORBIKE;
    }


}
