package caseStudies.ParkingLot.repositories;

import caseStudies.ParkingLot.models.Gate;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class GateRepository {
    private Map<Long, Gate> gates = new TreeMap<>();

    public Optional<Gate> findGateById(Long gateId) {
        if (gates.containsKey(gateId)) {
            return Optional.of(gates.get(gateId));
        }
        return Optional.empty();
    }

    //gates will be updated if we have save method to save the gates
}
