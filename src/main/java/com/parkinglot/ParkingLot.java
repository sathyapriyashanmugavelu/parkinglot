package com.parkinglot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    private static int capacity;
    private Set<Parkable> parkedVechile= new HashSet<Parkable>();

    public ParkingLot(int availableSpace)
    {
        capacity=availableSpace;
    }

    public void park(Parkable vehicle) throws NoSlotAvailableException,VehicleAlreadyParkedException {
        if(isSpaceAvailable())
        {
           if(!parkedVechile.add(vehicle))
           {
               throw new VehicleAlreadyParkedException();
           }
        }
    }

    private boolean isSpaceAvailable() throws NoSlotAvailableException{
        int availableSpace=capacity-parkedVechile.size();
        if(availableSpace==0)
        {
            throw new NoSlotAvailableException();
        }
        return true;
    }
}