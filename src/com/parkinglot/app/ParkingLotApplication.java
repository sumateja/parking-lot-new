package com.parkinglot.app;

import com.parkinglot.common.*;
import com.parkinglot.model.*;
import com.parkinglot.ticketing.Ticket;

import java.time.LocalDateTime;
import java.util.*;

public class ParkingLotApplication  implements Parkable{


    private String name;

    private LocationEnum parkingLotType;

    private List<Slot> twoWheelerSlots;
    private List<Slot> fourWheelerSlots;
    private List<Slot> heavyVehicleSlots;


    private static ParkingLotApplication parkingLotApp =null;

    public ParkingLotApplication() {
        this.twoWheelerSlots = new ArrayList<>();
        this.fourWheelerSlots = new ArrayList<>();
        this.heavyVehicleSlots = new ArrayList<>();
    }

    public static ParkingLotApplication getParkingLotInstance(){
        if(parkingLotApp==null)
            parkingLotApp= new ParkingLotApplication();
        return parkingLotApp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


  public boolean initializeParkingSlots(LocationEnum location,int numberOfTwoWheelerSlots , int numberOfFourWheelerSlots , int numberOfHeavyVehicleSlot) throws ParkingNotAllowedForVehicleException {

      if(numberOfHeavyVehicleSlot>0 && (LocationEnum.STADIUM==location || LocationEnum.AIRPORT ==location))
            throw new ParkingNotAllowedForVehicleException("ERROR: Buses/Trucks not allowed in "+location);

      this.parkingLotType = location;

      for(int i=1;i<=numberOfTwoWheelerSlots;i++) twoWheelerSlots.add(new Slot(i, ParkingSlotType.TWO_WHEELER));

      for(int i=1;i<=numberOfFourWheelerSlots;i++){
          fourWheelerSlots.add(new Slot(i,ParkingSlotType.FOUR_WHEELER));
      }

      for(int i=1;i<=numberOfHeavyVehicleSlot;i++){
          heavyVehicleSlots.add(new Slot(i,ParkingSlotType.HEAVY_BUS_TRUCK));
      }

      System.out.println("Created Two wheeler parking with "+twoWheelerSlots.size()+" slots");
      System.out.println("Created Four wheeler parking with "+fourWheelerSlots.size()+" slots");
      System.out.println("Created Heavy Vehicle parking with "+heavyVehicleSlots.size()+" slots");

      return true;
  }

    public Ticket park(Vehicle vehicle) throws ParkingFullException, ParkingNotAllowedForVehicleException {
        Slot nextAvailableSlot = null;

        if((VehicleType.BUS.equals(vehicle.getVehicleType()) || VehicleType.TRUCK.equals(vehicle.getVehicleType()))&&
                (LocationEnum.STADIUM== this.parkingLotType || LocationEnum.AIRPORT ==this.parkingLotType))
            throw new ParkingNotAllowedForVehicleException("ERROR: Buses/Trucks not allowed in "+this.parkingLotType.toString());

        if(Utils.getVehicleParkingSlot(vehicle.getVehicleType()).equals(ParkingSlotType.TWO_WHEELER)){
            nextAvailableSlot = Utils.getNextAvailableSlot(twoWheelerSlots,ParkingSlotType.TWO_WHEELER);
        }else if(Utils.getVehicleParkingSlot(vehicle.getVehicleType()).equals(ParkingSlotType.FOUR_WHEELER)){
            nextAvailableSlot = Utils.getNextAvailableSlot(fourWheelerSlots,ParkingSlotType.FOUR_WHEELER);
        }else if(Utils.getVehicleParkingSlot(vehicle.getVehicleType()).equals(ParkingSlotType.HEAVY_BUS_TRUCK))
            nextAvailableSlot = Utils.getNextAvailableSlot(heavyVehicleSlots,ParkingSlotType.HEAVY_BUS_TRUCK);

        nextAvailableSlot.parkVehicle(vehicle);
        LocalDateTime entryDate= LocalDateTime.now();

        System.out.println("Vehicle with number "+vehicle.getLicenseNumber() +" parked in Slot number :"+nextAvailableSlot.getSlotId()+", Entry time: "+entryDate);

        Ticket ticket = new Ticket(vehicle.getLicenseNumber(),entryDate , vehicle.getVehicleType(),nextAvailableSlot);

        return ticket;
    }

    public Double unPark(Ticket ticket) throws InvalidVehicleNumberException {
        Double costByHours= Double.valueOf(0);
        Slot slot = null;
        try{
            if(Utils.getVehicleParkingSlot(ticket.getVehicleType()).equals(ParkingSlotType.TWO_WHEELER)){
                slot= Utils.getVehicleSlotByVehicleNumber(ticket.getVehicleNumber(),twoWheelerSlots);
            }else if(Utils.getVehicleParkingSlot(ticket.getVehicleType()).equals(ParkingSlotType.FOUR_WHEELER)){
                slot= Utils.getVehicleSlotByVehicleNumber(ticket.getVehicleNumber(),fourWheelerSlots);
            }
            else if(Utils.getVehicleParkingSlot(ticket.getVehicleType()).equals(ParkingSlotType.HEAVY_BUS_TRUCK)){
                slot= Utils.getVehicleSlotByVehicleNumber(ticket.getVehicleNumber(),heavyVehicleSlots);
            }

            slot.unParkVehicle();
            LocalDateTime exitTime= LocalDateTime.now();
            Long hours= Utils.getHoursParked(ticket.getEntryDateTime(), exitTime);
            costByHours = Utils.getCostByHours(hours,ticket.getVehicleType(),parkingLotType);
            Utils.generateReceipt(ticket,exitTime);

            System.out.println("Vehicle with license number "+ticket.getVehicleNumber()+ " parked at Slot: "+slot.getSlotId()+" Was parked for"+
                    hours+" hours and was charged :"+costByHours);
        }
        catch (InvalidVehicleNumberException invalidVehicleNumberException){
            System.out.println(invalidVehicleNumberException.getMessage());
            throw invalidVehicleNumberException;
        }

        return costByHours;
    }

    public static void main(String[] args) throws ParkingNotAllowedForVehicleException, ParkingFullException, InvalidVehicleNumberException {
        ParkingLotApplication lot = getParkingLotInstance();
        lot.initializeParkingSlots(LocationEnum.MALL,5,2,5);

        Bus car1= new Bus("KA 1234");

       Ticket t1= lot.park(car1);

        Car car2= new Car("KA MJ 0007");

        lot.park(car2);

        lot.unPark(t1);

    }
}
