package com.example.demo.dto;

import lombok.Data;

@Data
public class FlightSearchDTO {
    private String origin;
    private String destination;
    private String departureDate;
    private int adults;
}
