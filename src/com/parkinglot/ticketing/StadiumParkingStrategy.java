package com.parkinglot.ticketing;

import com.parkinglot.common.Utils;
import com.parkinglot.common.VehicleType;
import com.parkinglot.model.ParkingSlotType;

public class StadiumParkingStrategy implements ParkingStrategy{
    @Override
    public Double getParkingFee(Long hours, VehicleType vehicleType) {
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
}
