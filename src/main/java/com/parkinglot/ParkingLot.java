package com.parkinglot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    private static int capacity;
    private Set<Parkable> parkedVehicles = new HashSet<Parkable>();
    private static ArrayList<Notifiable> notifiableList = new ArrayList<>();

    public ParkingLot(int availableSpace) {
        capacity = availableSpace;
    }

    public void park(Parkable vehicle) throws NoSlotAvailableException, VehicleAlreadyParkedException {
        if (parkedVehicles.contains(vehicle)) {
            throw new VehicleAlreadyParkedException();
        }
        if (!isSpaceAvailable()) {
            throw new NoSlotAvailableException();
        }
        add(vehicle);
    }

    private void add(Parkable vehicle) {
        parkedVehicles.add(vehicle);
        if (!isSpaceAvailable()) {
            sendNotification();
        }
    }

    private void sendNotification() {
        for (Notifiable notifiable : notifiableList) {
            if (notifiable != null) {
                notifiable.notifyFull();
            }
        }
    }

    private boolean isSpaceAvailable() {
        int availableSpace = capacity - parkedVehicles.size();
        return availableSpace > 0;
    }

    public void unPark(Parkable vehicle) throws NoVehicleToUnparkException {
        if (!parkedVehicles.contains(vehicle)) {
            throw new NoVehicleToUnparkException();
        }
        parkedVehicles.remove(vehicle);
    }

    public void addSubscribers(ArrayList<Notifiable> subscribersList) {
        for (Notifiable notifiable : subscribersList) {
            if (notifiable != null) {
                notifiableList.add(notifiable);
            }
        }

    }
}