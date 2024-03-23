package caseStudies.ParkingLot.strategies;

import caseStudies.ParkingLot.models.Gate;
import caseStudies.ParkingLot.models.ParkingLot;
import caseStudies.ParkingLot.models.ParkingSpot;
import caseStudies.ParkingLot.models.VehicleType;

import java.util.Optional;

public interface SpotAssignmentStrategy {

    Optional<ParkingSpot> findSpot(VehicleType vehicleType, ParkingLot parkingLot);
}
