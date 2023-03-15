package com.parkinglot.model;

import com.parkinglot.common.VehicleType;

import java.time.LocalDateTime;

public class Slot {

    int slotId;

    boolean isAvailable;

    Vehicle vehicle;

    ParkingSlotType parkingSlotType;

    public Slot(int id , ParkingSlotType parkingSlotType){
        this.slotId = id;
        this.parkingSlotType = parkingSlotType;
        this.isAvailable = true;
    }

    public void parkVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
        this.isAvailable = false;
    }

    public LocalDateTime unParkVehicle(){
        this.vehicle = null;
        this.isAvailable = true;

        return LocalDateTime.now();
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int id) {
        this.slotId = id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }


    public ParkingSlotType getParkingSlotType() {
        return parkingSlotType;
    }

    public void setParkingSlotType(ParkingSlotType parkingSlotType) {
        this.parkingSlotType = parkingSlotType;
    }
}
