package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    class ParkableTest implements Parkable {
        @Override
        public boolean park() {
            return false;
        }
    }

    @Test
    void shouldBeSuccessfullyParkWhenSpaceAvailable() {
        ParkingLot parkinglot = new ParkingLot(1);
        Assertions.assertDoesNotThrow(() -> {
            parkinglot.park(new ParkableTest());
        });
    }

    @Test
    void shouldNotParkWhenSpaceNotAvailable() {
        ParkingLot parkinglot = new ParkingLot(0);
        Assertions.assertThrows(NoSlotAvailableException.class,() -> {
            parkinglot.park(new ParkableTest());
        });
    }

    @Test
    void shouldNotParkWhenTheSameVehicleAlreadyParked() throws NoSlotAvailableException {
        ParkingLot parkinglot = new ParkingLot(2);
        Parkable vehicle = new ParkableTest();
        parkinglot.park(vehicle);
    }
    @Test
    void shouldParkDifferentVehicles() throws NoSlotAvailableException {
        ParkingLot parkinglot = new ParkingLot(2);
        parkinglot.park(new ParkableTest());
    }

}
