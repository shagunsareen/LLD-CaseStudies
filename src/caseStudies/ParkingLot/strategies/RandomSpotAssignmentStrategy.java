package caseStudies.ParkingLot.strategies;

import caseStudies.ParkingLot.models.*;

import java.util.Optional;

public class RandomSpotAssignmentStrategy implements SpotAssignmentStrategy{

    @Override
    public Optional<ParkingSpot> findSpot(VehicleType vehicleType, ParkingLot parkingLot) {
        for(ParkingFloor parkingFloor : parkingLot.getParkingFloors()){
            for(ParkingSpot parkingSpot : parkingFloor.getParkingSpots()){
                if((SpotStatus.AVAILABLE.equals(parkingSpot.getSpotStatus()))
                        && (parkingSpot.getVehicleType().contains(vehicleType))){
                    return Optional.of(parkingSpot);
                }
            }
        }
        return Optional.empty();
    }
}
