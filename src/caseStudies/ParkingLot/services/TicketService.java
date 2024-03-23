package caseStudies.ParkingLot.services;

import caseStudies.ParkingLot.exceptions.NoAvailableSpotException;
import caseStudies.ParkingLot.repositories.ParkingLotRepository;
import caseStudies.ParkingLot.exceptions.InvalidGateException;
import caseStudies.ParkingLot.models.*;
import caseStudies.ParkingLot.repositories.GateRepository;
import caseStudies.ParkingLot.repositories.TicketRepository;
import caseStudies.ParkingLot.repositories.VehicleRepository;
import caseStudies.ParkingLot.strategies.SpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {

    TicketRepository ticketRepository;

    VehicleRepository vehicleRepository;

    GateRepository gateRepository;

    SpotAssignmentStrategy spotAssignmentStrategy;

    ParkingLotRepository parkingLotRepository;

    public TicketService(VehicleRepository vehicleRepository,
                         GateRepository gateRepository,
                         ParkingLotRepository parkingLotRepository,
                         TicketRepository ticketRepository,
                         SpotAssignmentStrategy spotAssignmentStrategy){
        this.vehicleRepository = vehicleRepository;
        this.gateRepository = gateRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
    }

    public Ticket generateTicket(String vehicleNumber, VehicleType vehicleType, Long gateId) throws InvalidGateException, NoAvailableSpotException {
        //Checking the gate and operator
        Optional<Gate> gateOptional = gateRepository.findGateById(gateId);
        if(gateOptional.isEmpty()){
            throw new InvalidGateException("Gate number entered is invalid. Please enter again");
        }

        Gate gate = gateOptional.get();
        Operator operator = gate.getOperator();

        //Getting the Vehicle
        Optional<Vehicle> vehicleOptional = vehicleRepository.findVehicleByNumber(vehicleNumber);
        Vehicle vehicle;

        if(vehicleOptional.isEmpty()){
           // no existing vehicle exists with this number, then create a new one
            vehicle = new Vehicle(vehicleNumber, vehicleType);
            vehicle = vehicleRepository.save(vehicle);
        }else{
            vehicle = vehicleOptional.get();
        }

        // We will have different strategies to assign parking spots, so we need spot Assignment strategies

        //for parking spot we need parking lot. find parking lot by gate id
        Optional<ParkingLot> parkingLot  = parkingLotRepository.findParkingLotOfGate(gate);
        if(parkingLot.isEmpty()){
            throw new RuntimeException();

        }

        Optional<ParkingSpot> parkingSpot = spotAssignmentStrategy.findSpot(vehicleType, parkingLot.get());
        if(parkingSpot.isEmpty()){
            throw new NoAvailableSpotException("No spots are available");
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
