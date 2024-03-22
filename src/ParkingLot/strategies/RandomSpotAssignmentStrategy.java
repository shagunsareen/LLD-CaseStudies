package ParkingLot.strategies;

import ParkingLot.models.*;

import java.util.Optional;

public class RandomSpotAssignmentStrategy implements SpotAssignmentStrategy{

    @Override
    public Optional<ParkingSpot> findSpot(VehicleType vehicleType, ParkingLot parkingLot, Gate gate) {
            for(ParkingFloor parkingFloor : parkingLot.getParkingFloors()){
                for(ParkingSpot parkingSpot : parkingFloor.getParkingSpot()){
                    if(parkingSpot.getSpotStatus().equals(SpotStatus.AVAILABLE) &&
                            parkingSpot.getSupportedVehicles().contains(vehicleType)){
                        return Optional.of(parkingSpot);
                    }
                }
            }

            return Optional.empty();
    }
}
