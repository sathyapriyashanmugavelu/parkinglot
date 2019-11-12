package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    class ParkableTest implements Parkable {
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
    void shouldNotParkWhenTheSameVehicleAlreadyParked() throws NoSlotAvailableException ,VehicleAlreadyParkedException{
        ParkingLot parkinglot = new ParkingLot(2);
        Parkable vehicle = new ParkableTest();
        parkinglot.park(vehicle);

        Assertions.assertThrows(VehicleAlreadyParkedException.class,() -> {
            parkinglot.park(vehicle);
        });
    }
    @Test
    void shouldParkDifferentVehicles() throws NoSlotAvailableException,VehicleAlreadyParkedException {
        ParkingLot parkinglot = new ParkingLot(2);
        parkinglot.park(new ParkableTest());

        Assertions.assertDoesNotThrow(() -> {
            parkinglot.park(new ParkableTest());
        });
    }

    @Test
    void shouldNotUnParkWhenVehicleNotParked(){
        ParkingLot parkinglot = new ParkingLot(1);
        ParkableTest vehicle=new ParkableTest();

        Assertions.assertThrows(VehicleDoesNotExistToUnParkException.class,() -> {
         parkinglot.unPark(vehicle);
        });
    }

    @Test
    void shouldBeSuccessfulWhenUnParkParkedVehicle() throws NoSlotAvailableException,VehicleAlreadyParkedException{
        ParkingLot parkinglot = new ParkingLot(1);
        ParkableTest vehicle=new ParkableTest();
        parkinglot.park(vehicle);

        Assertions.assertDoesNotThrow(() -> {
            parkinglot.unPark(vehicle);
        });
    }

    @Test
    void shouldBeSuccessfulWhenParkAfterUnParked() throws NoSlotAvailableException,VehicleAlreadyParkedException,VehicleDoesNotExistToUnParkException{
        ParkingLot parkinglot = new ParkingLot(1);
        ParkableTest vehicle=new ParkableTest();
        parkinglot.park(vehicle);
        parkinglot.unPark(vehicle);

        Assertions.assertDoesNotThrow(() -> {
            parkinglot.park(vehicle);
        });
    }

}
