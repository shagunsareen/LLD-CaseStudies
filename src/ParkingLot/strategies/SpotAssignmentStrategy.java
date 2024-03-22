package ParkingLot.strategies;

import ParkingLot.models.Gate;
import ParkingLot.models.ParkingLot;
import ParkingLot.models.ParkingSpot;
import ParkingLot.models.VehicleType;

import java.util.Optional;

public interface SpotAssignmentStrategy {
    Optional<ParkingSpot> findSpot(VehicleType vehicleType, ParkingLot parkingLot, Gate gate);
}
