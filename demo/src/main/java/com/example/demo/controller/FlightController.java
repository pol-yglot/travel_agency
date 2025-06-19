package com.example.demo.controller;

import com.example.demo.dto.FlightResponseDTO;
import com.example.demo.dto.FlightSearchDTO;
import com.example.demo.service.AmadeusApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class FlightController {

    private final AmadeusApiService amadeusApiService;

    @GetMapping("/flight_search")
    public String searchForm() {
        return "flight/flight_search";
    }

    @PostMapping("/flight_search")
    public ResponseEntity<?> search(@RequestBody FlightSearchDTO dto) {
        return amadeusApiService.searchFlights(dto);
    }
}
