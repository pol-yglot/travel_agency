package com.example.demo.controller;

import com.example.demo.dto.FlightResponseDTO;
import com.example.demo.dto.FlightSearchDTO;
import com.example.demo.service.AmadeusApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FlightController {
    private final AmadeusApiService apiService;

    @GetMapping("/flight/search")
    public String searchForm() {
        return "flight/search_form";
    }

    @PostMapping("/flight/search")
    public String searchResult(@ModelAttribute FlightSearchDTO dto, Model model) {
        return "flight/search_result";
    }
}
