package com.example.demo.dto;

import lombok.Data;

@Data
public class FlightResponseDTO {
    private String airline;
    private String departure;
    private String arrival;
    private String departureTime;
    private String arrivalTime;
    private String price;
}
