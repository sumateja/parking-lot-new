package com.parkinglot.ticketing;

import com.parkinglot.common.VehicleType;

 public interface ParkingStrategy {

    public abstract Double getParkingFee(Long hours, VehicleType vehicleType);
}
