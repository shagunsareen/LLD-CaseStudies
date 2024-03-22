package ParkingLot.services;

import ParkingLot.exceptions.InvalidGateException;
import ParkingLot.exceptions.NoAvailableSpotException;
import ParkingLot.models.*;
import ParkingLot.repositories.IGateRepository;
import ParkingLot.repositories.ParkingLotRepository;
import ParkingLot.repositories.TicketRepository;
import ParkingLot.repositories.VehicleRepository;
import ParkingLot.strategies.SpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {

    private IGateRepository gateRepository;
    private VehicleRepository vehicleRepository;

    private SpotAssignmentStrategy spotAssignmentStrategy;

    private TicketRepository ticketRepository;

    private ParkingLotRepository parkingLotRepository;

    public TicketService(IGateRepository gateRepository,
                         VehicleRepository vehicleRepository,
                         SpotAssignmentStrategy spotAssignmentStrategy,
                         TicketRepository ticketRepository,
                         ParkingLotRepository parkingLotRepository){
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
        this.ticketRepository = ticketRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    public Ticket generateTicket(Long gateId, VehicleType vehicleType, String vehicleNumber) throws InvalidGateException, NoAvailableSpotException {
        /*
        Vehicle = check if aleady in db. if yes, get that else create
        ParkingSpot = strategy
        Gate = get gate for that if from db. else throw an exception
        Operator = from gate
         */

        Optional<Gate> gateOptional = gateRepository.findGateById(gateId);
        if(gateOptional.isEmpty()){
            throw new InvalidGateException();
        }

        Gate gate = gateOptional.get();

        Operator operator = gate.getOperator();
        Vehicle vehicle;

        Optional<Vehicle> vehicleOptional = vehicleRepository.findVehicleByNumber(vehicleNumber);
        if(vehicleOptional.isEmpty()){
            vehicle = new Vehicle();
            vehicle.setVehicleNumber(vehicleNumber);
            vehicle.setVehicleType(vehicleType);
            vehicle = vehicleRepository.save(vehicle);
        }else{
            vehicle = vehicleOptional.get();
        }

        Optional<ParkingLot> parkingLot = parkingLotRepository.getParkingLotOfGate(gate);
        if(parkingLot.isEmpty()){
            throw new RuntimeException();

        }
        Optional<ParkingSpot> parkingSpot = spotAssignmentStrategy.findSpot(vehicleType, parkingLot.get(), gate);
        if(parkingSpot.isEmpty()){
            throw new NoAvailableSpotException();
        }
        Ticket ticket = new Ticket();
        ticket.setGate(gate);
        ticket.setOperator(operator);
        ticket.setVehicle(vehicle);
        ticket.setParkingSpot(parkingSpot.get());
        ticket.setEntryTime(new Date());

        return ticketRepository.save(ticket);
    }
}
