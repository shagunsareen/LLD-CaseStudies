package ParkingLot.models;

import java.util.List;
public class ParkingFloor extends BaseModel{
    private List<ParkingSpot> parkingSpot;
    private int floorNumber;

    public List<ParkingSpot> getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(List<ParkingSpot> parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
}
