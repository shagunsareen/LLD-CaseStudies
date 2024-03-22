package ParkingLot.repositories;

import ParkingLot.models.Gate;
import ParkingLot.models.Operator;

import java.util.Optional;

public interface IGateRepository {
    Optional<Gate> findGateById(Long gateId);
}
