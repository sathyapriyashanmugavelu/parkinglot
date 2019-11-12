package com.parkinglot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    private static int capacity;
    private Set<Parkable> parkedVehicles= new HashSet<Parkable>();

    public ParkingLot(int availableSpace)
    {
        capacity=availableSpace;
    }

    public void park(Parkable vehicle) throws NoSlotAvailableException,VehicleAlreadyParkedException {
        if(isSpaceAvailable())
        {
           if(!parkedVehicles.add(vehicle))
           {
               throw new VehicleAlreadyParkedException();
           }
        }
    }

    private boolean isSpaceAvailable() throws NoSlotAvailableException{
        int availableSpace=capacity-parkedVehicles.size();
        if(availableSpace==0)
        {
            throw new NoSlotAvailableException();
        }
        return true;
    }

    public void unPark(Parkable vehicle) throws VehicleDoesNotExistToUnParkException {
        if(!parkedVehicles.remove(vehicle))
        {
            throw new VehicleDoesNotExistToUnParkException();
        }
    }
}