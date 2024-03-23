package caseStudies.ParkingLot.controllers;

import caseStudies.ParkingLot.dto.GenerateTicketRequestDto;
import caseStudies.ParkingLot.dto.GenerateTicketResponseDto;
import caseStudies.ParkingLot.exceptions.InvalidGateException;
import caseStudies.ParkingLot.exceptions.NoAvailableSpotException;
import caseStudies.ParkingLot.models.ResponseStatus;
import caseStudies.ParkingLot.models.VehicleType;
import caseStudies.ParkingLot.models.Ticket;
import caseStudies.ParkingLot.services.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto request) {
        String vehicleNumber = request.getVehicleNum();
        VehicleType vehicleType = request.getVehicleType();
        Long gateId = request.getGateId();

        Ticket ticket;
        GenerateTicketResponseDto responseDto = new GenerateTicketResponseDto();

        try {
            ticket = ticketService.generateTicket(vehicleNumber, vehicleType, gateId);
        } catch (InvalidGateException e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            responseDto.setMessage("Gate ID is invalid");
            return responseDto;
        } catch (NoAvailableSpotException e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            responseDto.setMessage("No parking slot available");
            return responseDto;
        }

        responseDto.setTicketId(ticket.getId());
        responseDto.setOperatorName(ticket.getOperator().getName());
        responseDto.setSpotNumber(ticket.getParkingSpot().getNumber());
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        return responseDto;
    }
}
