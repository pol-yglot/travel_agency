package com.example.demo.service;

import com.example.demo.dto.FlightSearchDTO;
import org.springframework.http.ResponseEntity;

public interface AmadeusApiService {
    ResponseEntity<?> searchFlights(FlightSearchDTO dto);
}
