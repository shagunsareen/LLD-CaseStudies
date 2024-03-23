package caseStudies.ParkingLot.repositories;

import caseStudies.ParkingLot.models.Gate;
import caseStudies.ParkingLot.models.ParkingLot;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class ParkingLotRepository {
    Map<Long, ParkingLot> parkingLots = new TreeMap<>();

    public Optional<ParkingLot> findParkingLotOfGate(Gate gate) {
        for(ParkingLot parkingLot : parkingLots.values()){
            if(parkingLot.getGates().contains(gate)){
                return Optional.of(parkingLot);
            }
        }
        return Optional.empty();
    }
}
