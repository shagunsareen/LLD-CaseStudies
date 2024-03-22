package ParkingLot.repositories;

import ParkingLot.models.Ticket;

import java.util.Map;
import java.util.TreeMap;

public class TicketRepository {
    private Map<Long, Ticket> ticketMap = new TreeMap<>();

    private long lastSavedId = 0L;
    public Ticket save(Ticket ticket){
       ticket.setId(lastSavedId + 1);
       lastSavedId += 1;
       ticketMap.put(lastSavedId, ticket);
       return ticket;
    }
}
