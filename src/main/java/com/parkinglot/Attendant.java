package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class Attendant{
    private List<ParkingLot> parkingLotList=new ArrayList<>();

    public Attendant(List<ParkingLot> LotList){
        this.parkingLotList=LotList;
    }

    public void attendantPark(Parkable car) throws NoSlotAvailableException, VehicleAlreadyParkedException {
        for(ParkingLot parkingLot :parkingLotList){
            if(parkingLot.isSpaceAvailable()){
                parkingLot.park(car);
                return;
            }
        }
        throw new NoSlotAvailableException();
    }


    public void attendantUnPark(){

    }
}
