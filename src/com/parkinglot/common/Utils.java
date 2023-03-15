package com.parkinglot.common;

import com.parkinglot.model.ParkingSlotType;
import com.parkinglot.model.Slot;
import com.parkinglot.model.Vehicle;
import com.parkinglot.ticketing.Ticket;

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
        if(LocationEnum.MALL == parkingLotType){
            cost = getCostByHoursForMall(hours,vehicleSize);
        }else if(LocationEnum.STADIUM == parkingLotType){
            cost = getCostByHoursForStadium(hours,vehicleSize);
        }
        else if(LocationEnum.AIRPORT == parkingLotType){
            cost = getCostByHoursForAirport(hours,vehicleSize);
        }

        return cost;
    }

    private static Double getCostByHoursForAirport(Long hours, VehicleType vehicleType) {
        Double cumulativeCost= Double.valueOf(0);
        Long days=0l;
        if(Utils.getVehicleParkingSlot(vehicleType) == ParkingSlotType.TWO_WHEELER)
        {
            if(hours==0 || hours <1)
                cumulativeCost = 0.0;
            if (hours>1 && hours <8)
                cumulativeCost = 40.00;
            if ( hours >8 && hours <24 )
                cumulativeCost = 60.00;
            if ( hours >24 ){
                    days =(hours/24);
                    if(hours%24!=0) days++;
                cumulativeCost = 80.00*(days);
            }
        }
         if (Utils.getVehicleParkingSlot(vehicleType) == ParkingSlotType.FOUR_WHEELER){
             if(hours>=0 || hours <12)
                 cumulativeCost = 60.00 ;
             if(hours>=12 && hours <=24)
                 cumulativeCost = 80.00 ;
             if ( hours >24 ){
                 days =(hours/24);
                 if(hours%24!=0) days++;
                 cumulativeCost = 100.00*days;
             }
         }


        return cumulativeCost;
    }

    private static Double getCostByHoursForStadium(Long hours, VehicleType vehicleType) {
        Double cumulativeCost= Double.valueOf(0);

        if(Utils.getVehicleParkingSlot(vehicleType) == ParkingSlotType.TWO_WHEELER)
        {
            if(hours <=3 || (hours-3)>=0 )
                cumulativeCost+= 30.0;
            if((hours-8)>=0)
                cumulativeCost += 60.0;
            if((hours-12)>=0){
                cumulativeCost += 100.0*(hours-12);
            }
        }

        if(Utils.getVehicleParkingSlot(vehicleType) == ParkingSlotType.FOUR_WHEELER)
        {
            if((hours-3)>=0 )
                cumulativeCost+= 60.0;
            if((hours-8)>=0)
                cumulativeCost += 120.0;
            if((hours-12)>=0)
                cumulativeCost += 200.0*(hours-12);
        }

        return cumulativeCost;
    }

    private static Double getCostByHoursForMall(Long hours, VehicleType vehicleType) {
        Double flatRate= Double.valueOf(0);

        if(hours == 0) hours++;

        if(Utils.getVehicleParkingSlot(vehicleType) == ParkingSlotType.TWO_WHEELER)
            flatRate = Constants.MALL_TWO_WHEELER_FLAT_RATE;
        else if (Utils.getVehicleParkingSlot(vehicleType) == ParkingSlotType.FOUR_WHEELER)
            flatRate = Constants.MALL_FOUR_WHEELER_FLAT_RATE;
        else if (Utils.getVehicleParkingSlot(vehicleType) == ParkingSlotType.HEAVY_BUS_TRUCK)
            flatRate = Constants.MALL_HEAVY_VEHICLE_FLAT_RATE;

        return flatRate*hours;
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
