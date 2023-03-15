import com.parkinglot.common.LocationEnum;
import com.parkinglot.common.Utils;
import com.parkinglot.common.VehicleType;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void getHoursParked() {
        LocalDateTime entryTime=LocalDateTime.now();
        LocalDateTime exitTime= entryTime.plusHours(3).plusMinutes(3);
        assertEquals( 4, Utils.getHoursParked(entryTime,exitTime),0.01);
    }

    @Test
    public void getCostByHoursForMall() {
        //MALL Parking lot
        // Bike parked for 3 hours 30 minute
        assertEquals(Double.valueOf(40.0),Utils.getCostByHours(4l, VehicleType.MOTORBIKE, LocationEnum.MALL));

        // Car parked for 6 hours 1 minute
        assertEquals(Double.valueOf(140.0),Utils.getCostByHours(7l, VehicleType.SUV, LocationEnum.MALL));

        // Truck parked for 1 hours 59 minute
        assertEquals(Double.valueOf(100.0),Utils.getCostByHours(2l, VehicleType.TRUCK, LocationEnum.MALL));
    }

    @Test
    public void getCostByHoursForStadium() {
        //Stadium Parking lot
        // Bike parked for 3 hours 40 minute
        assertEquals(Double.valueOf(30.0),Utils.getCostByHours(4l, VehicleType.SCOOTER, LocationEnum.STADIUM));

        // Bike parked for 14 hours 59 minute
        assertEquals(Double.valueOf(390.0),Utils.getCostByHours(15l, VehicleType.SCOOTER, LocationEnum.STADIUM));

        // Electric SUV Car parked for 11 hours 30 minute
        assertEquals(Double.valueOf(180.0),Utils.getCostByHours(12l, VehicleType.SUV, LocationEnum.STADIUM));

        // SUV Car parked for 13 hours 5 minute
        assertEquals(Double.valueOf(580.0),Utils.getCostByHours(14l, VehicleType.ELECTRIC_SUV, LocationEnum.STADIUM));
    }

    @Test
    public void getCostByHoursForAirport() {
        //Airport Parking lot
        // Bike parked for  55 minute
        assertEquals(Double.valueOf(0.0),Utils.getCostByHours(1l, VehicleType.SCOOTER, LocationEnum.AIRPORT));

        // Bike parked for 14 hours 59 minute
        assertEquals(Double.valueOf(60.0),Utils.getCostByHours(15l, VehicleType.MOTORBIKE, LocationEnum.AIRPORT));

        // Bike parked for 1 Day 12 hours
        assertEquals(Double.valueOf(160.0),Utils.getCostByHours(36l, VehicleType.MOTORBIKE, LocationEnum.AIRPORT));

        //  Car parked for  50 minute
        assertEquals(Double.valueOf(60.0),Utils.getCostByHours(1l, VehicleType.SUV, LocationEnum.AIRPORT));


        // SUV Car parked for 23 hours 59 minute
        assertEquals(Double.valueOf(80.0),Utils.getCostByHours(24l, VehicleType.SUV, LocationEnum.AIRPORT));

        //  Car parked for  3 days 1 hour
        assertEquals(Double.valueOf(400.0),Utils.getCostByHours(74l, VehicleType.SUV, LocationEnum.AIRPORT));
    }

}