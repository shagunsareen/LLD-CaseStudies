package caseStudies.ParkingLot.dto;

import caseStudies.ParkingLot.models.VehicleType;

public class GenerateTicketRequestDto {
    private String vehicleNum;
    private VehicleType vehicleType;
    private Long gateId;

    public GenerateTicketRequestDto(String vehicleNum, VehicleType vehicleType, Long gateId) {
        this.vehicleNum = vehicleNum;
        this.vehicleType = vehicleType;
        this.gateId = gateId;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(String vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Long getGateId() {
        return gateId;
    }

    public void setGateId(Long gateId) {
        this.gateId = gateId;
    }
}
