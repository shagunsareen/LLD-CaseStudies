package ParkingLot;

import ParkingLot.controllers.TicketController;
import ParkingLot.repositories.GateRepository;
import ParkingLot.repositories.ParkingLotRepository;
import ParkingLot.repositories.TicketRepository;
import ParkingLot.repositories.VehicleRepository;
import ParkingLot.services.TicketService;
import ParkingLot.strategies.RandomSpotAssignmentStrategy;
import ParkingLot.strategies.SpotAssignmentStrategy;

public class ParkingLotApplication {

    public static void main(String[] args) {
        //TOPOLOGICAL WAY TO CREATE OBJECTS -- reverse order
        GateRepository gateRepository = new GateRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        TicketRepository ticketRepository = new TicketRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();
        SpotAssignmentStrategy spotAssignmentStrategy = new RandomSpotAssignmentStrategy();

        TicketService ticketService = new TicketService(
                gateRepository,
                vehicleRepository,
                spotAssignmentStrategy,
                ticketRepository,
                parkingLotRepository
        );

        TicketController ticketController = new TicketController(ticketService);

        System.out.println("Application has started on port :8080");

    }
}
