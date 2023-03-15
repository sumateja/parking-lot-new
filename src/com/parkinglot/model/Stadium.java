package com.parkinglot.model;

import com.parkinglot.common.InvalidVehicleNumberException;
import com.parkinglot.common.ParkingFullException;
import com.parkinglot.common.ParkingNotAllowedForVehicleException;
import com.parkinglot.ticketing.Ticket;

public class Stadium implements Parkable {
    public Stadium(int numberOfTwoWheelerSlots, int numberOfFourWheelerSlots) {
        super();
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
