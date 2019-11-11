package com.parkinglot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void shouldBeSuccessfullyParkWhenSpaceAvailable()
    {
        ParkingLot parkinglot =  new ParkingLot(1);
        boolean isParked = parkinglot.park();
        Assertions.assertTrue(isParked);
    }
    @Test
    void shouldNotParkWhenSpaceNotAvailable()
    {
        ParkingLot parkinglot =  new ParkingLot(0);
        boolean isParked = parkinglot.park();

        Assertions.assertFalse(isParked);
    }
}
