package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class Attendant{
    private List<ParkingLot> parkingLotList=new ArrayList<>();

    public Attendant(List<ParkingLot> LotList){
        this.parkingLotList=LotList;
    }

    public void attendantPark(Parkable car) throws AllParkingLotsFullException, NoSlotAvailableException, VehicleAlreadyParkedException {
        for(ParkingLot parkingLot :parkingLotList){
            if(parkingLot.isSpaceAvailable()){
                parkingLot.park(car);
                return;
            }
        }
        throw new AllParkingLotsFullException();
    }


    public void attendantUnPark(){

    }
}
