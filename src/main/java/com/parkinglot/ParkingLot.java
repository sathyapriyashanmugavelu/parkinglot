package com.parkinglot;

public class ParkingLot {
    private int availableSpace;

    public ParkingLot(int availableSpace)
    {
        this.availableSpace=availableSpace;
    }

    public boolean park() {
        if (isSpaceAvailable()) {
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