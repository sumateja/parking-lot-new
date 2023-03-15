package com.parkinglot.model;

import com.parkinglot.common.InvalidVehicleNumberException;
import com.parkinglot.common.ParkingFullException;
import com.parkinglot.common.ParkingNotAllowedForVehicleException;
import com.parkinglot.ticketing.Ticket;

public  interface Parkable {

        public Ticket park(Vehicle vehicle) throws ParkingFullException, ParkingNotAllowedForVehicleException;

        public Double unPark(Ticket ticket) throws InvalidVehicleNumberException;

    }

