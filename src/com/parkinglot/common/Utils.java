package com.parkinglot.common;

import com.parkinglot.model.ParkingSlotType;
import com.parkinglot.model.Slot;
import com.parkinglot.model.Vehicle;
import com.parkinglot.ticketing.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Utils {

    public static long getHoursParked(LocalDateTime entryDate, LocalDateTime exitDate) {
        Long hours= Duration.between(entryDate,exitDate).toHours();

        Long minutes= Duration.between(entryDate,exitDate).toMinutes();
        if(minutes % 60 !=0) hours++;

        return hours;
    }


    public static Double getCostByHours(Long hours, VehicleType vehicleSize, LocationEnum parkingLotType) {
        Double cost= Double.valueOf(0);
        ParkingStrategy strategy = null;
        if(LocationEnum.MALL == parkingLotType){
            strategy = new FlatFeeParkingStrategy();
        }else if(LocationEnum.STADIUM == parkingLotType){
            strategy = new StadiumParkingStrategy();
        }
        else if(LocationEnum.AIRPORT == parkingLotType){
            strategy = new AirportParkingStrategy();
        }

        cost =strategy.getParkingFee(hours,vehicleSize);
        return cost;
    }


    public static Slot getNextAvailableSlot(List<Slot> slots, ParkingSlotType vehicleType) throws ParkingFullException {
        return  slots.stream().filter(slot -> slot.isAvailable()).findFirst().orElseThrow(
                () -> {
                    return  new ParkingFullException("No slots available for Vehicle Type"+vehicleType.toString());});
    }

    public static Slot getVehicleSlotByVehicleNumber(String vehicleNumber, List<Slot> slots) throws InvalidVehicleNumberException {
        for (Slot slot: slots) {
            Vehicle vehicle = slot.getVehicle();
            if(vehicle!=null && vehicle.getLicenseNumber().equalsIgnoreCase(vehicleNumber)){
                return slot;
            }
        }
        throw new InvalidVehicleNumberException("Vehicle with license plate"+vehicleNumber+" Not found");
    }

    public static String generateReceipt(Ticket ticket,LocalDateTime exitTime){
        System.out.println("====PARKING RECEIPT====");
        System.out.println("Receipt Number: "+"R-"+ticket.getTicketNumber());
        System.out.println("Entry Time: "+ticket.getEntryDateTime());
        System.out.println("Exit Time: "+exitTime);

        return "R-"+ticket.getTicketNumber();
    }

    public static ParkingSlotType getVehicleParkingSlot(VehicleType vehicleType){

        ParkingSlotType slot = null;
        if(vehicleType.equals(VehicleType.SCOOTER) || vehicleType.equals(VehicleType.MOTORBIKE))
            slot = ParkingSlotType.TWO_WHEELER;
        if (vehicleType.equals(VehicleType.SUV) || vehicleType.equals(VehicleType.ELECTRIC_SUV) || vehicleType.equals(VehicleType.CAR))
            slot= ParkingSlotType.FOUR_WHEELER;
        if (vehicleType.equals(VehicleType.BUS)|| vehicleType.equals(VehicleType.TRUCK))
            slot= ParkingSlotType.HEAVY_BUS_TRUCK;

        return  slot;
    }

}
