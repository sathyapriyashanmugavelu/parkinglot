package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

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

        Assertions.assertThrows(NoSlotAvailableException.class, () -> {
            parkinglot.park(new ParkableTest());
        });
    }

    @Test
    void shouldNotParkWhenTheSameVehicleAlreadyParked() throws NoSlotAvailableException, VehicleAlreadyParkedException {
        ParkingLot parkinglot = new ParkingLot(2);
        Parkable vehicle = new ParkableTest();
        parkinglot.park(vehicle);

        Assertions.assertThrows(VehicleAlreadyParkedException.class, () -> {
            parkinglot.park(vehicle);
        });
    }

    @Test
    void shouldParkDifferentVehicles() throws NoSlotAvailableException, VehicleAlreadyParkedException {
        ParkingLot parkinglot = new ParkingLot(2);
        parkinglot.park(new ParkableTest());

        Assertions.assertDoesNotThrow(() -> {
            parkinglot.park(new ParkableTest());
        });
    }

    @Test
    void shouldNotUnParkWhenVehicleNotParked() {
        ParkingLot parkinglot = new ParkingLot(1);
        ParkableTest vehicle = new ParkableTest();

        Assertions.assertThrows(NoVehicleToUnparkException.class, () -> {
            parkinglot.unPark(vehicle);
        });
    }

    @Test
    void shouldBeSuccessfulWhenUnParkParkedVehicle() throws NoSlotAvailableException, VehicleAlreadyParkedException {
        ParkingLot parkinglot = new ParkingLot(1);
        ParkableTest vehicle = new ParkableTest();
        parkinglot.park(vehicle);

        Assertions.assertDoesNotThrow(() -> {
            parkinglot.unPark(vehicle);
        });
    }

    @Test
    void shouldBeSuccessfulWhenParkAfterUnParked() throws NoSlotAvailableException, VehicleAlreadyParkedException, NoVehicleToUnparkException {
        ParkingLot parkinglot = new ParkingLot(1);
        ParkableTest vehicle = new ParkableTest();
        parkinglot.park(vehicle);
        parkinglot.unPark(vehicle);

        Assertions.assertDoesNotThrow(() -> {
            parkinglot.park(vehicle);
        });
    }

    @Test
    void shouldNotifyWhenParkingLotFull() throws NoSlotAvailableException, VehicleAlreadyParkedException, NoVehicleToUnparkException {
        Notifiable owner = mock(Notifiable.class);
        ParkingLot parkinglot = new ParkingLot(1);
        ArrayList notifiableList=new ArrayList();
        notifiableList.add(owner);
        parkinglot.addSubscribers(notifiableList);

        ParkableTest vehicle = new ParkableTest();
        parkinglot.park(vehicle);

        verify(owner).notifyFull();
    }

    @Test
    void shouldNotNotifyWhenParkingLotNotFull() throws NoSlotAvailableException, VehicleAlreadyParkedException, NoVehicleToUnparkException {
        Notifiable owner = mock(Notifiable.class);
        ParkingLot parkinglot = new ParkingLot(2);
        ParkableTest vehicle = new ParkableTest();
        parkinglot.park(vehicle);
        ArrayList notifiableList=new ArrayList();
        notifiableList.add(owner);
        parkinglot.addSubscribers(notifiableList);

        verify(owner, never()).notifyFull();
    }

    @Test
    void shouldNotifyAllSubscribersWhenParkingLotFull() throws NoSlotAvailableException, VehicleAlreadyParkedException, NoVehicleToUnparkException {
        Notifiable owner = mock(Notifiable.class);
        Notifiable security = mock(Notifiable.class);
        ParkingLot parkinglot = new ParkingLot(1);
        List<Notifiable> notifiableList= new ArrayList<>();
        notifiableList.add(owner);
        notifiableList.add(security);
        parkinglot.addSubscribers(notifiableList);

        ParkableTest vehicle = new ParkableTest();
        parkinglot.park(vehicle);

        verify(owner).notifyFull();
        verify(security).notifyFull();
    }

}
