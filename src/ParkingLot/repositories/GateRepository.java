package ParkingLot.repositories;

import ParkingLot.models.Gate;

import java.util.Optional;
import java.util.TreeMap;
import java.util.Map;

public class GateRepository implements IGateRepository{

    //store gate by gateid as key
    private Map<Long, Gate> gates = new TreeMap<>();

    public Optional<Gate> findGateById(Long gateId){
        if(gates.containsKey(gateId)){
            return Optional.of(gates.get(gateId));
        }

        return Optional.empty();
    }
}
