package com.parkinglot.ticketing;

import com.parkinglot.model.Slot;
import com.parkinglot.common.VehicleType;

import java.time.LocalDateTime;

public class Ticket {

    private static int ticketNumberSequence=1;
    private String vehicleNumber;
    private LocalDateTime entryDateTime;
    private LocalDateTime exitDateTime;
    private VehicleType vehicleType;
    private int ticketNumber;
    private Slot slot;

    public Ticket(String vehicleNumber, LocalDateTime entryDateTime, VehicleType vehicleType, Slot slot) {
        super();
        this.ticketNumber = ticketNumberSequence++;
        this.vehicleNumber = vehicleNumber;
        this.entryDateTime = entryDateTime;
        this.exitDateTime =null;
        this.vehicleType = vehicleType;
        this.slot = slot;
        System.out.println("====PARKING TICKET:====");
        System.out.println("Parking Ticket number: "+ticketNumber);
        System.out.println("Entry Time: "+entryDateTime);
        System.out.println("Spot Number: "+slot.getSlotId());
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }


    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public LocalDateTime getEntryDateTime() {
        return entryDateTime;
    }

    public void setEntryDateTime(LocalDateTime entryDateTime) {
        this.entryDateTime = entryDateTime;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "slotNumber=" + ticketNumber +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", date=" + entryDateTime +
                ", vehicleSize=" + vehicleType +
                '}';
    }
}
