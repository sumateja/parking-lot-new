import com.parkinglot.common.LocationEnum;
import com.parkinglot.common.ParkingFullException;
import com.parkinglot.common.ParkingNotAllowedForVehicleException;
import com.parkinglot.model.Bus;
import com.parkinglot.model.MotorBike;
import com.parkinglot.app.ParkingLotApplication;
import com.parkinglot.ticketing.Ticket;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class ParkingLotApplicationTest {

    ParkingLotApplication parkinglot;

    @Before
    public void getParkingLotInstance() {
        parkinglot = ParkingLotApplication.getParkingLotInstance();

    }


    //Check for successfull parking
    @Test
    public void initializeParkingSlots() throws ParkingNotAllowedForVehicleException, ParkingFullException {

        parkinglot.initializeParkingSlots(LocationEnum.MALL,3,0,0);

        MotorBike bike1= new MotorBike("KA341234");
        Ticket t1= parkinglot.park(bike1);
        assertNotNull(t1.getTicketNumber());
        assertNotNull(t1.getSlot().getSlotId());

    }

    //Check for Parking Full exception
    @Test(expected = ParkingFullException.class)
    public void checkParkingFull() throws ParkingNotAllowedForVehicleException, ParkingFullException {

        parkinglot.initializeParkingSlots(LocationEnum.MALL,1,0,0);

        MotorBike bike1= new MotorBike("KA341234");
        Ticket t1= parkinglot.park(bike1);
        assertNotNull(t1.getTicketNumber());
        MotorBike bike2= new MotorBike("KA341235");
        parkinglot.park(bike2);
    }

    //Check for Parking Not allowed  exception

    @Test(expected = ParkingNotAllowedForVehicleException.class)
    public void checkCreateParkingNotAllowed() throws ParkingNotAllowedForVehicleException, ParkingFullException {

        parkinglot.initializeParkingSlots(LocationEnum.STADIUM,0,0,1);
        parkinglot.initializeParkingSlots(LocationEnum.AIRPORT,0,0,1);

    }

    //Check for Parking Not allowed exception
    @Test(expected = ParkingNotAllowedForVehicleException.class)
    public void checkParkingNotAllowed() throws ParkingNotAllowedForVehicleException, ParkingFullException {

        parkinglot.initializeParkingSlots(LocationEnum.STADIUM,0,0,1);
        Bus bus= new Bus("KA341234");
        Ticket t1= parkinglot.park(bus);
        assertEquals(1,t1.getTicketNumber());
    }



}