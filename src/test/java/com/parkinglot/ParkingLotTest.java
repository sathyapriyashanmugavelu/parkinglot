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
        boolean isParked = parkinglot.park(new ParkableTest());
        Assertions.assertTrue(isParked);
    }

    @Test
    void shouldNotParkWhenSpaceNotAvailable() {
        ParkingLot parkinglot = new ParkingLot(0);
        boolean isParked = parkinglot.park(new ParkableTest());

        Assertions.assertFalse(isParked);
    }

    @Test
    void shouldNotParkWhenTheSameVehicleAlreadyParked() {
        ParkingLot parkinglot = new ParkingLot(2);
        Parkable vehicle = new ParkableTest();
        parkinglot.park(vehicle);
        Assertions.assertFalse(parkinglot.park(vehicle));
    }
    
}
