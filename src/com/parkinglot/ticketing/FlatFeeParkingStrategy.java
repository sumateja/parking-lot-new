package com.parkinglot.ticketing;

import com.parkinglot.common.Constants;
import com.parkinglot.common.Utils;
import com.parkinglot.common.VehicleType;
import com.parkinglot.model.ParkingSlotType;

public class FlatFeeParkingStrategy implements ParkingStrategy{
    @Override
    public Double getParkingFee(Long hours, VehicleType vehicleType) {
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
}
