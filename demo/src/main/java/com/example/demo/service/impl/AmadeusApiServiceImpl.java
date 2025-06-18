package com.example.demo.service.impl;

import com.example.demo.dto.FlightSearchDTO;
import com.example.demo.service.AmadeusApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AmadeusApiServiceImpl implements AmadeusApiService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${amadeus.api.clientId}")
    private String clientId;

    @Value("${amadeus.api.clientSecret}")
    private String clientSecret;

    @Value("${amadeus.api.url.search}")
    private String flightSearchUrl;

    @Value("${amadeus.api.url.token}")
    private String tokenUrl;

    private String getAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        Map<String, String> body = new HashMap<>();
        body.put("grant_type", "client_credentials");
        body.put("client_id", clientId);
        body.put("client_secret", clientSecret);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null && response.getBody().get("access_token") != null) {
            return (String) response.getBody().get("access_token");
        }

        throw new RuntimeException("Amadeus API 토큰 요청 실패");
    }

    @Override
    public ResponseEntity<?> searchFlights(FlightSearchDTO dto) {
        String token = getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = flightSearchUrl +
                "?originLocationCode=" + dto.getOrigin() +
                "&destinationLocationCode=" + dto.getDestination() +
                "&departureDate=" + dto.getDepartureDate() +
                "&adults=" + dto.getAdults();

        HttpEntity<Void> request = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, request, String.class);
    }
}
