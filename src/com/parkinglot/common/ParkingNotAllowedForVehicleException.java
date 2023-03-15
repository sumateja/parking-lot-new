package com.parkinglot.common;

public class ParkingNotAllowedForVehicleException extends Exception {
    public ParkingNotAllowedForVehicleException(String message) {
        super(message);
    }

}
