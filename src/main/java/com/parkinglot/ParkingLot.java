package com.parkinglot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    private static int capacity;
    private int availableSpace;
    private Set<Parkable> parkedVechile= new HashSet<Parkable>();

    public ParkingLot(int availableSpace)
    {
        capacity=availableSpace;
        this.availableSpace=availableSpace;
    }

    public boolean park(Parkable vehicle) {
        if (isSpaceAvailable() && parkedVechile.add(vehicle)) {
                availableSpace--;
                return true;
        }
        return false;
    }

    public boolean isSpaceAvailable() {
        if (availableSpace != 0) {
            return true;
        }
        return false;
    }
}