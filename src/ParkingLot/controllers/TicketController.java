package ParkingLot.controllers;

import ParkingLot.dto.GenerateTicketRequestDto;
import ParkingLot.dto.GenerateTicketResponseDto;
import ParkingLot.exceptions.InvalidGateException;
import ParkingLot.exceptions.NoAvailableSpotException;
import ParkingLot.models.ResponseStatus;
import ParkingLot.models.Ticket;
import ParkingLot.models.VehicleType;
import ParkingLot.services.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    //This is not the best way as the entire object is not required
    /*
    public void generateTicket(Vehicle vehicle, Gate gate){}
     */

    //what if i have to add another param then all clients using this function then this creates a problem
    /*public GenerateTicketResponseDTO generateTicket(String vehicleNum, VehicleType vehicleType, Long gateId){

    }*/

    //Never take params as it is , Never return params as it is
    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto request){
        String vehicleNumber = request.getVehicleNum();
        VehicleType vehicleType = request.getVehicleType();
        Long gateId = request.getGateId();

        Ticket ticket = new Ticket();
        GenerateTicketResponseDto response = new GenerateTicketResponseDto();

        try{
            ticket =  ticketService.generateTicket(gateId, vehicleType, vehicleNumber);
        }catch (InvalidGateException e){
            response.setResponseStatus(ResponseStatus.FAILURE);
            response.setMessage("Gate ID is invalid");
            return response;
        }catch (NoAvailableSpotException e){
            response.setResponseStatus(ResponseStatus.FAILURE);
            response.setMessage("No parking slot available");
            return response;
        }

        response.setTicketId(ticket.getId());
        response.setResponseStatus(ResponseStatus.SUCCESS);
        response.setOperatorName(ticket.getOperator().getName());
        response.setSpotNumber(ticket.getParkingSpot().getNumber());

        return response;
    }
}
