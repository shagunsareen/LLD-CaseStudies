package ParkingLot.repositories;

import ParkingLot.models.Gate;
import ParkingLot.models.ParkingLot;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class ParkingLotRepository {

    //hashmap can't do range queries fast , so we use treemap because it is sorted and can do range query faster compared to hashmap
    private Map<Long, ParkingLot> parkingLots = new TreeMap<>();

    public Optional<ParkingLot> getParkingLotOfGate(Gate gate){
       for(ParkingLot parkingLot : parkingLots.values()){
           if(parkingLot.getGates().contains(gate)){
               return Optional.of(parkingLot);
           }
       }
       return Optional.empty();
    }
}
