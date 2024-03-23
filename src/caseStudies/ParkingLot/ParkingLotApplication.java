package caseStudies.ParkingLot;

import caseStudies.ParkingLot.dto.GenerateTicketRequestDto;
import caseStudies.ParkingLot.dto.GenerateTicketResponseDto;
import caseStudies.ParkingLot.models.Ticket;
import caseStudies.ParkingLot.models.VehicleType;
import caseStudies.ParkingLot.strategies.RandomSpotAssignmentStrategy;
import caseStudies.ParkingLot.strategies.SpotAssignmentStrategy;
import caseStudies.ParkingLot.repositories.GateRepository;
import caseStudies.ParkingLot.repositories.ParkingLotRepository;
import caseStudies.ParkingLot.repositories.TicketRepository;
import caseStudies.ParkingLot.repositories.VehicleRepository;
import caseStudies.ParkingLot.services.TicketService;
import caseStudies.ParkingLot.controllers.TicketController;

public class ParkingLotApplication {
    public static void main(String[] args) {

        VehicleRepository vehicleRepository = new VehicleRepository();
        TicketRepository ticketRepository = new TicketRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        GateRepository gateRepository = new GateRepository();
        SpotAssignmentStrategy spotAssignmentStrategy = new RandomSpotAssignmentStrategy();

        TicketService ticketService = new TicketService(vehicleRepository, gateRepository,
                                                        parkingLotRepository, ticketRepository, spotAssignmentStrategy);
        TicketController ticketController = new TicketController(ticketService);
        GenerateTicketRequestDto requestDto = new GenerateTicketRequestDto("1A131", VehicleType.CAR, 1L);

        GenerateTicketResponseDto responseDto = ticketController.generateTicket(requestDto);
        System.out.println(responseDto.toString());
        System.out.println("Application has started on port :8080");
    }
}
