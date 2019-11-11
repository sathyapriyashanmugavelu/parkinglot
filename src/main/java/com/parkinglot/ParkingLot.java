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

    public boolean park(Parkable vehicle) {
        return (isSpaceAvailable() && parkedVechile.add(vehicle));
    }

    private boolean isSpaceAvailable() {
        int availableSpace=capacity-parkedVechile.size();
        return availableSpace>0;
    }
}