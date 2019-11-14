package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AttendantTest {

    @Test
    public void ShouldParkWhenSpacesAreAvailable() throws Exception{
        ParkingLot availableLot = mock(ParkingLot.class);
        List<ParkingLot> allLots = new ArrayList<ParkingLot>();
        allLots.add(availableLot);
        Attendant attendant = new Attendant(allLots);
        Parkable car = mock(Parkable.class);
        when(availableLot.isSpaceAvailable()).thenReturn(true);

        attendant.attendantPark(car);

        verify(availableLot).park(car);
    }
    @Test
    public void ShouldBeAllowedToParkInAvailableLotWhenOneLotIsFull() throws Exception{

        ParkingLot fullLot = mock(ParkingLot.class);
        Parkable car= mock(Parkable.class);
        ParkingLot availableLot = mock(ParkingLot.class);
        List<ParkingLot> allLots = new ArrayList<ParkingLot>();
        allLots.add(fullLot);
        allLots.add(availableLot);
        Attendant attendant = new Attendant(allLots);
        Parkable anotherCar = mock(Parkable.class);
        when(fullLot.isSpaceAvailable()).thenReturn(false);
        when(availableLot.isSpaceAvailable()).thenReturn(true);

        attendant.attendantPark(anotherCar);

        verify(availableLot).park(anotherCar);
    }

    @Test
    public void ShouldNotBeAllowedToParkWhenAllLotsFull() throws Exception{

        ParkingLot fullLot = mock(ParkingLot.class);
        ParkingLot availableLot = mock(ParkingLot.class);
        List<ParkingLot> allLots = new ArrayList<ParkingLot>();
        allLots.add(fullLot);
        allLots.add(availableLot);
        Attendant attendant = new Attendant(allLots);
        Parkable car = mock(Parkable.class);
        when(fullLot.isSpaceAvailable()).thenReturn(false);
        when(availableLot.isSpaceAvailable()).thenReturn(false);

        assertThrows(AllParkingLotsFullException.class, () ->
                attendant.attendantPark(car));
    }




}
