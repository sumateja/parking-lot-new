package com.parkinglot.ticketing;

import com.parkinglot.common.Utils;
import com.parkinglot.common.VehicleType;
import com.parkinglot.model.ParkingSlotType;

public class AirportParkingStrategy implements ParkingStrategy{
    @Override
    public Double getParkingFee(Long hours, VehicleType vehicleType) {
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
}
