package com.parkinglot;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    private static int capacity;
    private Set<Parkable> parkedVehicles = new HashSet<Parkable>();
    Owner owner;

    public ParkingLot(int availableSpace) {
        capacity = availableSpace;
    }

    public ParkingLot(int availableSpace,Owner owner) {
        capacity = availableSpace;
        this.owner = owner;
    }

    public void park(Parkable vehicle) throws NoSlotAvailableException, VehicleAlreadyParkedException {
        if (parkedVehicles.contains(vehicle)) {
            throw new VehicleAlreadyParkedException();
        }
        if (!isSpaceAvailable()) {
            throw new NoSlotAvailableException();
        }
            add(vehicle) ;
    }
    private void add(Parkable vehicle){
        parkedVehicles.add(vehicle);
        if(owner != null && !isSpaceAvailable()){
            owner.notifyOwner();
        }
    }

    private boolean isSpaceAvailable()  {
        int availableSpace = capacity - parkedVehicles.size();
        return availableSpace>0;
    }

    public void unPark(Parkable vehicle) throws NoVehicleToUnparkException {
        if (!parkedVehicles.contains(vehicle)) {
            throw new NoVehicleToUnparkException();
        }
        parkedVehicles.remove(vehicle);
    }
}