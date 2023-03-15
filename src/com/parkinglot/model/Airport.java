package com.parkinglot.model;

import com.parkinglot.common.InvalidVehicleNumberException;
import com.parkinglot.common.ParkingFullException;
import com.parkinglot.common.ParkingNotAllowedForVehicleException;
import com.parkinglot.ticketing.Ticket;

public class Airport implements Parkable {
    public Airport(int numberOfTwoWheelerSlots, int numberOfFourWheelerSlots) {
    }

    @Override
    public Ticket park(Vehicle vehicle) throws ParkingFullException, ParkingNotAllowedForVehicleException {
        return null;
    }

    @Override
    public Double unPark(Ticket ticket) throws InvalidVehicleNumberException {
        return null;
    }
}
